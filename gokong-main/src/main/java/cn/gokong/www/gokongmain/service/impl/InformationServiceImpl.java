package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.InformationMapper;
import cn.gokong.www.gokongmain.domain.Information;
import cn.gokong.www.gokongmain.domain.SysConfig;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.DefualtEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.grab.WeChartArticle;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.information.PageQueryInformationHomeOutput;
import cn.gokong.www.gokongmain.vo.information.QueryArticleContentOuput;
import cn.gokong.www.gokongmain.vo.information.QueryInformationDetailsOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import cn.ikeek.www.ftp.FTPUtil;
import cn.ikeek.www.reptile.bean.ArticleEntity;
import cn.ikeek.www.reptile.util.ReptileUtil;
import cn.ikeek.www.reptile.util.TouTiaoUtil;
import cn.ikeek.www.reptile.util.WeiXinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 资讯信息表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    CollectionInfoService collectionInfoService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    FansInfoService fansInfoService;

    @Autowired
    PrivilegeUserService privilegeUserService;

    /**
     * 分页获取我发布的资讯
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryInformationCollectionOutput> pageQueryMyInformation(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryMyInformation(userNo, pageNo, pageSize);
    }

    /**
     * 分页查询资讯首页列表
     *
     * @param keyword  关键字
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryInformationHomeOutput> pageQueryHomeInformation(String keyword, Integer pageNo, Integer pageSize) {
        //查询资讯信息
        List<PageQueryInformationHomeOutput> pageQueryInformationCollectionOutputs = baseMapper.pageQueryHomeInformation(keyword, pageNo, pageSize);
        return pageQueryInformationCollectionOutputs;
    }

    /**
     * 查询资讯详情
     *
     * @param userNo        用户编码
     * @param informationNo 资讯编码
     * @return
     */
    @Override
    public QueryInformationDetailsOutput queryInformationDetails(String userNo, String informationNo) {
        //查询资讯详情
        QueryInformationDetailsOutput queryInformationDetailsOutput = baseMapper.queryInformationDetails(informationNo);

        //查询用户信息
        UserInfo userInfo = userInfoService.findByUserNo(queryInformationDetailsOutput.getUserNo());
        if (ObjectUtil.isNotNull(userInfo)) {
            queryInformationDetailsOutput.setUserHead(userInfo.getUserHead());
            queryInformationDetailsOutput.setNickName(userInfo.getNickName());
        }

        //判断是否收藏
        boolean collection = collectionInfoService.isCollection(userNo, informationNo);
        queryInformationDetailsOutput.setCollection(collection);

        //判断是否关注当前作者
        boolean focus = fansInfoService.isFocus(queryInformationDetailsOutput.getUserNo(), userNo);
        //判断作者是否是自己
        if (userInfo.getUserNo().equals(userNo)) {
            queryInformationDetailsOutput.setFocus(true);
        } else {
            queryInformationDetailsOutput.setFocus(focus);
        }

        //添加资讯点击
        SysConfig clickConfig = sysConfigService.findBySysCode("10010002");
        if (ObjectUtil.isNotNull(clickConfig)) {
            baseMapper.updateClickNum(clickConfig.getConfigVal(), informationNo);
        } else {
            baseMapper.updateClickNum(1, informationNo);
        }

        queryInformationDetailsOutput.setInformationContent(HttpUtil.get(queryInformationDetailsOutput.getInformationUrl()));

        return queryInformationDetailsOutput;
    }

    /**
     * 发布资讯
     *
     * @param userNo             用户编码
     * @param informationMode    资讯模式
     * @param informationName    资讯名称
     * @param informationPhoto   资讯图片
     * @param informationContent 资讯内容
     * @param photoSizeType      资讯布局
     * @return
     */
    @Override
    @Transactional
    public Map<String, String> releaseInformation(String userNo, String informationMode, String informationName, String informationPhoto, String informationContent, String photoSizeType) throws IOException {
        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(userNo, "information");
        if (hesPrivilege) {
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)) {
                userNo = randomUserInfo.getUserNo();
            }
        }

        //将内容转为标准的html格式
        informationContent = ReptileUtil.htmlStr(informationContent, informationName);

        Document parse = Jsoup.parse(informationContent);

        Elements imgs = parse.select("img");

        Ftp ftp = new Ftp("47.98.139.112", 8021,"sunwukongroot","qazxsw");
        ftp.setMode(FtpMode.Passive);
        //进入远程目录
        ftp.cd("/data/vsftpd/gokong_file/html");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(informationContent.getBytes());
        String fileName = UUID.randomUUID()+".html";
        //上传本地文件
        boolean upload = ftp.upload("/data/vsftpd/gokong_file/html",fileName, byteArrayInputStream);
        StaticLog.info("上传资讯：{}",upload);
        //上传资讯文件获取资讯链接
        String informationUrl = "https://api.gokong.cn/ftp/html/"+fileName;

        Information information = new Information();
        information.setId(DataBaseTool.createId());
        information.setUserNo(userNo);
        information.setInformationName(informationName);
        information.setInformationMode(informationMode);
        information.setInformationNo(DataBaseTool.createNo("zx"));

        information.setClickNum(0);
        information.setEvaluateNum(0);
        information.setReleaseDate(DataBaseTool.createDate());
        information.setSetTopState(SysCodeEnum.CODE_10240001.getCode());
        information.setInformationUrl(informationUrl);
        information.setInformationPhoto(informationPhoto);
        information.setPhotoSizeType(photoSizeType);

        //如果图片为空就插入默认图片
        if (StrUtil.isEmpty(information.getInformationPhoto())) {

            if (imgs.size() > 0) {
                int imgLayout = ReptileUtil.getImgLayout(imgs.get(0).attr("src"));
                information.setPhotoSizeType(imgLayout==1?SysCodeEnum.CODE_10400002.getCode():SysCodeEnum.CODE_10400001.getCode());
                information.setInformationPhoto(imgs.get(0).attr("src"));
            }else {
                information.setInformationPhoto(DefualtEnum.CODE_10010002.getUrl());
                information.setPhotoSizeType(SysCodeEnum.CODE_10400001.getCode());
            }
        }
        //如果图片布局为空 就获取获取图片布局方式
        if (StrUtil.isEmpty(information.getPhotoSizeType())) {
            information.setPhotoSizeType(WeChartArticle.getImgSizeType(information.getInformationPhoto()));
        }
        save(information);

        Map<String, String> map = new HashMap<>();
        map.put("informationNo", information.getInformationNo());
        return map;
    }

    /**
     * 删除资讯
     *
     * @param informationNo 资讯编码
     * @return
     */
    @Override
    @Transactional
    public boolean delInformation(String informationNo) throws IOException {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("information_no", informationNo);
        Information information = getOne(wrapper);
        if (ObjectUtil.isNotNull(information)) {
            boolean removeById = removeById(information.getId());
            if (removeById) {
                ThreadUtil.execAsync(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            List<String> articleImg = WeChartArticle.getArticleImg(information.getInformationUrl());
                            FTPUtil.delFile(articleImg);
                            if (!information.getInformationPhoto().contains("defualt")) {
                                FTPUtil.delFile(information.getInformationPhoto());
                            }
                            FTPUtil.delFile(information.getInformationUrl());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
            return removeById;
        }
        return false;
    }

    /**
     * 根据链接地址获取内容
     *
     * @param url  链接地址
     * @param type 链接类型
     * @return
     */
    @Override
    public QueryArticleContentOuput queryArticleContent(String url, String type){
        String location = HttpRequest.get(url).setFollowRedirects(false).execute().header("Location");
        String host = "";
        if (location!=null){
            host = URLUtil.url(location).getHost();
        }else {
            host = URLUtil.url(url).getHost();
        }
        QueryArticleContentOuput queryArticleContentOuput = new QueryArticleContentOuput();
        ArticleEntity articleEntity = null;
        if (host.contains("weixin")){
            //微信文章
            articleEntity = WeiXinUtil.getArticleEntity(url);
        }else if (host.contains("toutiao")){
            //头条文章
            articleEntity = TouTiaoUtil.getArticleEntity(url);
        }else if(host.contains("api.gokong.cn")){
            articleEntity = new ArticleEntity();
            String htmlStr = HttpUtil.get(url);
            Document parse = Jsoup.parse(htmlStr);
            int imgLayout = 0;
            String titleImg = "";
            String title = parse.select("title").text();
            String content = parse.select("body").html();
            Elements imgs = parse.select("img");
            if (imgs.size()>0){
                imgLayout = ReptileUtil.getImgLayout(imgs.get(0).attr("src"));
                titleImg = imgs.get(0).attr("src");
            }else {
                imgLayout = 2;
                titleImg = DefualtEnum.CODE_10010002.getUrl();
            }
            articleEntity.setLayout(imgLayout);
            articleEntity.setTitle(title);
            articleEntity.setCoverImg(titleImg);
            articleEntity.setContent(content);
        }else {
            throw new GrilException("不支持的文章类型,请换一篇试试。");
        }
        queryArticleContentOuput.setContent(articleEntity.getContent());
        queryArticleContentOuput.setImgLayout(articleEntity.getLayout()==1?SysCodeEnum.CODE_10400002.getCode():SysCodeEnum.CODE_10400001.getCode());
        queryArticleContentOuput.setTitle(articleEntity.getTitle());
        queryArticleContentOuput.setTitleImg(articleEntity.getCoverImg());
        return queryArticleContentOuput;
    }

    /**
     * 修改资讯
     *
     * @param informationNo      资讯编码
     * @param informationMode    资讯模式
     * @param informationName    资讯名称
     * @param informationPhoto   资讯图片
     * @param informationContent 资讯内容
     * @param photoSizeType      资讯布局
     * @return
     */
    @Override
    @Transactional
    public Map<String,String> updateInformation(String informationNo, String informationMode, String informationName, String informationPhoto, String informationContent, String photoSizeType) throws IOException {
        Map<String, String> map = new HashMap<>();
        //将内容转为标准的html格式
        informationContent = ReptileUtil.htmlStr(informationContent, informationName);

        //上传资讯文件获取资讯链接
        Ftp ftp = new Ftp("47.98.139.112", 8021,"sunwukongroot","qazxsw");
        ftp.setMode(FtpMode.Passive);
        //进入远程目录
        ftp.cd("/data/vsftpd/gokong_file/html");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(informationContent.getBytes());
        String fileName = UUID.randomUUID()+".html";
        //上传本地文件
        boolean upload = ftp.upload("/data/vsftpd/gokong_file/html",fileName, byteArrayInputStream);
        StaticLog.info("上传资讯：{}",upload);
        //上传资讯文件获取资讯链接
        String informationUrl = "https://api.gokong.cn/ftp/html/"+fileName;

        //查询资讯信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("information_no", informationNo);
        Information information = getOne(wrapper);

        if (ObjectUtil.isNotNull(information)) {
            String informationUrlPath = information.getInformationUrl();

            information.setInformationMode(informationMode);
            information.setInformationName(informationName);
            information.setInformationPhoto(informationPhoto);
            information.setPhotoSizeType(photoSizeType);
            information.setInformationUrl(informationUrl);
            boolean saveOrUpdate = saveOrUpdate(information);
            if (saveOrUpdate) {
                ThreadUtil.execAsync(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FTPUtil.delFile(informationUrlPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
        map.put("informationNo", informationNo);
        return map;
    }

    /**
     * 分页查询我关注的资讯
     *
     * @param keyword  关键字
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryInformationHomeOutput> pageQueryHomeFansInformation(String keyword, String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryHomeFansInformation(keyword, userNo, pageNo, pageSize);
    }

    @Override
    public Information findByInformationNo(String informationNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("information_no", informationNo);
        Information information = getOne(wrapper);
        if (ObjectUtil.isNull(information)) {
            throw new GrilException("未查询到相关资讯");
        }
        return information;
    }
}
