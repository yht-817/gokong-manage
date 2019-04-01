package cn.gokong.www.gokongmain.grab;

import cn.gokong.www.gokongmain.enums.DefualtEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.ftp.FTPUtil;
import cn.gokong.www.gokongmain.ftp.FtpDirEnum;
import cn.gokong.www.gokongmain.vo.information.QueryArticleContentOuput;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:微信文章
 *
 * @author Mick
 * CreateDate 2018/7/19/019 22:48
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class WeChartArticle {

    /**
     * 获取微信文章
     *
     * @param url
     *            请求地址
     * @return 微信文章
     * @throws IOException
     */
    public static QueryArticleContentOuput getWechartArticle(String url) throws IOException {
        if (!url.contains("https://mp.weixin.qq.com")){
            return null;
        }
        String htmlStr = HttpUtil.get(url);
        Document htmlDoc = Jsoup.parse(htmlStr);
        Elements tittle = htmlDoc.select("#activity-name");
        Elements author = htmlDoc.select(".rich_media_meta.rich_media_meta_text");
        Elements time = htmlDoc.select(".rich_media_meta.rich_media_meta_text#publish_time");
        Elements content = htmlDoc.select(".rich_media_content#js_content");

        //获取所有的图片
        Elements imgs = content.select("img");
        List<InputStream> inputStreams = new ArrayList<>(16);
        for (int i = 0; i < imgs.size(); i++) {
            //2.将所有的图片data-src 改为 src
            imgs.get(i).attr("src",imgs.get(i).attr("data-src"));
            imgs.get(i).removeAttr("data-src");
            inputStreams.add(FTPUtil.getImageStreamByUrl(imgs.get(i).attr("src")));
        }

        StaticLog.error("-------上传图片");
        List<String> list = FTPUtil.uploadStreamFile(FtpDirEnum.CODE_100010002,inputStreams);

        StaticLog.error("-------赋值新的图片地址给img");
        for (int i = 0; i < imgs.size(); i++) {
            imgs.get(i).attr("src",list.get(i));
        }
        //由于背景图片无法获取,那么将背景图片key置为空
        QueryArticleContentOuput articleEntity = getArticleEntity(tittle,imgs, content.html());

        //将内容放入文件
        //FileUtil.writeUtf8String(articleEntity.getContent(),"d:\\spider\\"+ DateUtil.today()+"\\index.html");
        return articleEntity;
    }

    private static QueryArticleContentOuput getArticleEntity(Elements tittle,Elements imgs, String content) throws IOException {
        //content = HtmlUtil.htmlStr(content,tittle.text());
        //去掉微信默认背景图
        content = content.replace("image:","");
        QueryArticleContentOuput articleEntity = new QueryArticleContentOuput();
        articleEntity.setTitle(tittle.text());
        if (imgs.size()>0){
            //获取第一张图片
            String src = imgs.get(0).attr("src");
            if (StrUtil.isNotEmpty(src)){
                URL url = new URL(src.replace("https","http"));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());

                String typeFromStream = HttpURLConnection.guessContentTypeFromStream(bufferedInputStream);
                //获取URL图片信息
                BufferedImage bufferedImage = null;
                if (typeFromStream.toLowerCase().contains("gif")){
                    GifDecoder.GifImage read = GifDecoder.read(bufferedInputStream);
                    bufferedImage = read.getFrame(0);
                }else {
                    bufferedImage = ImageUtil.read(bufferedInputStream);
                }

                //BufferedImage bufferedImage = ImageUtil.toImage(HttpUtil.get(src).getBytes());
                //获取图片长度
                int width = bufferedImage.getWidth();
                //获取图片宽度
                int height = bufferedImage.getHeight();
                if (width-height<=100){
                    //方图
                    articleEntity.setImgLayout(SysCodeEnum.CODE_10400002.getCode());
                }else if (width-height>=300){
                    //长图
                    articleEntity.setImgLayout(SysCodeEnum.CODE_10400001.getCode());
                }
                articleEntity.setTitleImg(src);
                StaticLog.error("图片长度:{}    图片宽度:{}",width,height);
                StaticLog.error("文章标题:{}", articleEntity.getTitle());
                StaticLog.error("文章图片:{}", articleEntity.getTitleImg());
                StaticLog.error("文章布局:{}","10400001".equals(articleEntity.getImgLayout())?"长图":"方图");
                StaticLog.error("抓取成功");
            }else {
                StaticLog.error("抓取微信文章,未找到合适的图片!");
                //插入默认图片
                articleEntity.setTitleImg(DefualtEnum.CODE_10010002.getUrl());
                articleEntity.setImgLayout(SysCodeEnum.CODE_10400001.getCode());
            }
        }else {
            StaticLog.error("抓取微信文章,未找到合适的图片!");
            //插入默认图片
            articleEntity.setTitleImg(DefualtEnum.CODE_10010002.getUrl());
            articleEntity.setImgLayout(SysCodeEnum.CODE_10400001.getCode());
        }
        articleEntity.setContent(content);

        return articleEntity;
    }

    /**
     * 获取本地文章内容
     * @param url
     * @return
     * @throws IOException
     */
    public static QueryArticleContentOuput getLocalhostArticle(String url) throws IOException {
        String articleHtml = HttpUtil.get(url);
        Document articlDoc = Jsoup.parse(articleHtml);
        Elements tittle = articlDoc.select("title");
        Elements content = articlDoc.select("body");
        //获取所有的图片
        Elements imgs = articlDoc.select("img");

        QueryArticleContentOuput articleEntity = getArticleEntity(tittle, imgs, content.html());
        return articleEntity;
    }

    public static void main(String[] args) throws MalformedURLException {
        String htmlStr = HttpUtil.get("https://mp.weixin.qq.com/s/cPeoZCX_J5J8PGG3X9o-Fg");

        Document wechartArticlDoc = Jsoup.parse(htmlStr);
        Elements tittle = wechartArticlDoc.select(".rich_media_title");
        Elements time = wechartArticlDoc.select(".rich_media_meta.rich_media_meta_text#post-date");
        Elements content = wechartArticlDoc.select(".rich_media_content#js_content");
        String alterContent = content.html().replace("data-src", "src");// 将属性data-src替换为src，否则图片不能正常显示
        Elements publicSign = wechartArticlDoc.select(".rich_media_meta.rich_media_meta_text.rich_media_meta_nickname");

        System.out.println(alterContent);

    }

    /**
     * 获取文章内所有的图片
     * @param url
     */
    public static List<String> getArticleImg(String url){
        String htmlStr = HttpUtil.get(url);
        Document htmlDoc = Jsoup.parse(htmlStr);
        //获取所有的图片
        Elements imgs = htmlDoc.select("img");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            list.add(imgs.get(i).attr("img"));
        }
        return list;
    }

    /**
     * 根据图片路径获取图片布局格式
     *
     * @param informationPhoto
     * @return
     */
    public static String getImgSizeType(String informationPhoto) throws MalformedURLException {
        //获取URL图片信息
        BufferedImage bufferedImage = ImageUtil.read(new URL(informationPhoto.replace("https","http")));
        //BufferedImage bufferedImage = ImageUtil.toImage(HttpUtil.get(src).getBytes());
        //获取图片长度
        int width = bufferedImage.getWidth();
        //获取图片宽度
        int height = bufferedImage.getHeight();
        if (width==height||width<height){
            //方图
            return SysCodeEnum.CODE_10400002.getCode();
        }else {
            //长图
            return SysCodeEnum.CODE_10400001.getCode();
        }
    }
}
