package cn.gokong.www.gokongmain.ftp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 说明:FTP工具类
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class FTPUtil {

    //private static FTPClient ftpClient = FtpConfig.getFtpClient();

    public static void main(String[] args) throws IOException {
        String s = uploadStringFile(FtpDirEnum.CODE_100010001, "asmdinsadad");
        System.out.println(s);
        /*boolean delFile = delFile("https://static.gokong.cn/img/userback/a");
        System.out.println(delFile);*/
    }

    /**
     * 上传FTP文件
     *
     * @param multipartFile 文件
     * @param ftpDirEnum    目录
     * @return
     */
    public static String uploadImage(MultipartFile multipartFile, FtpDirEnum ftpDirEnum){
        StaticLog.info("文件大小：{} byte {} KB", multipartFile.getSize(),(multipartFile.getSize() / 1024));
        if (multipartFile.getSize() > 1024 * 1024 * 0.5) {    //500KB
            return uploadImage(ftpDirEnum.getPath(), multipartFile, true);
        } else {
            return uploadImage(ftpDirEnum.getPath(), multipartFile, false);
        }

    }

    /**
     * 上传FTP文件
     *
     * @param multipartFiles 文件
     * @param ftpDirEnum    目录
     * @return
     */
    public static List<String> uploadImage(MultipartFile[] multipartFiles, FtpDirEnum ftpDirEnum){

        List<String> arr = new ArrayList<>();
        for (int i = 0; i < multipartFiles.length; i++) {
            StaticLog.info("文件大小：{}",multipartFiles[i].getSize());
            if (multipartFiles[i].getSize() > 1024 * 1024 * 1) {    //1M
                arr.add(uploadImage(ftpDirEnum.getPath(), multipartFiles[i], true));
            } else {
                arr.add(uploadImage(ftpDirEnum.getPath(), multipartFiles[i], false));
            }
        }
        return arr;
    }

    /**
     * 上传FTP文件
     * @param path          FTP目录
     * @param multipartFile 文件
     * @param compress      是否压缩
     * @return
     */
    public static String uploadImage(String path, MultipartFile multipartFile, boolean compress) {
        long start = System.currentTimeMillis();

        //生成新的文件名
        String fileName = UUID.randomUUID().toString() + "." + FTPUtil.getFileSuffix(multipartFile.getOriginalFilename());

        String ftpDir = FtpConfig.rootDir + path;

        FTPClient ftpClient = FtpConfig.getFtpClient();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            boolean makeDirectory = ftpClient.makeDirectory(ftpDir);
            StaticLog.info("创建目录：{}--{}",makeDirectory,ftpDir);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(ftpDir)){
                StaticLog.info("切换至FTP目录:{}",ftpDir);
                boolean uploadState = false;
                File dir = new File(ftpDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                if (compress){
                    Thumbnails
                            .of(multipartFile.getInputStream())    //原图文件的路径
                            .scale(0.5f)                        //图片长宽缩放比例
                            .outputQuality(0.4f)                //图片质量（越接近1质量越好）
                            .toFile(new File(ftpDir + fileName));
                    File file = new File(ftpDir + fileName);

                    uploadState = ftpClient.storeFile(fileName, new FileInputStream(file));
                    StaticLog.info("压缩");
                }else {
                    uploadState = ftpClient.storeFile(fileName, multipartFile.getInputStream());
                    StaticLog.info("不压缩");
                }
                StaticLog.info("FTP上传文件:{} 剩余:{},url:{}",uploadState,0,FtpConfig.path + path + fileName);

                /*File file = new File(ftpDir + fileName);

                BufferedImage read = ImageUtil.read(new URL(FtpConfig.path + path + fileName));*/

                long end = System.currentTimeMillis();
                StaticLog.info("耗时：{}秒",(end-start)/1000);
                //return FtpConfig.path + path + fileName + "?w=" + read.getWidth() + "&h=" + read.getHeight();
                return FtpConfig.path + path + fileName;
            }else {
                StaticLog.info("切换目录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;

    }

    /**
     * 上传FTP文件
     *
     * @param multipartFile 文件
     * @param ftpDirEnum    目录
     * @return
     */
    public static String uploadFile(MultipartFile multipartFile, FtpDirEnum ftpDirEnum) throws IOException {

        String path = ftpDirEnum.getPath();

        long start = System.currentTimeMillis();

        //生成新的文件名
        String fileName = UUID.randomUUID().toString() + "." + FTPUtil.getFileSuffix(multipartFile.getOriginalFilename());

        String ftpDir = FtpConfig.rootDir + path;

        FTPClient ftpClient = FtpConfig.getFtpClient();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            boolean makeDirectory = ftpClient.makeDirectory(ftpDir);
            StaticLog.info("创建目录：{}",makeDirectory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(ftpDir)){
                StaticLog.info("切换至FTP目录:{}",ftpDir);
                boolean uploadState = ftpClient.storeFile(fileName, multipartFile.getInputStream());
                StaticLog.info("FTP上传文件:{} 剩余:{},url:{}",uploadState,0,FtpConfig.path + path + fileName);

                long end = System.currentTimeMillis();
                StaticLog.info("耗时：{}秒",(end-start)/1000);
                return FtpConfig.path + path + fileName;
            }else {
                StaticLog.info("切换目录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;

    }

    /**
     * 单个上传文件
     * @param ftpDirEnum    ftp服务保存目录
     * @param str               字符串
     * @return
     */
    public static String uploadStringFile(FtpDirEnum ftpDirEnum, String str){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        String directory = ftpDirEnum.getPath();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            ftpClient.makeDirectory(directory);
            StaticLog.error("切换至FTP目录:{}",directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                String fileName = createFileName(null);
                try {

                    boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(str.getBytes()));
                    StaticLog.error("FTP上传文件:{} 剩余:{}",b,0);
                    return FtpConfig.path+directory+fileName;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;
    }

    /**
     * 批量上传文件
     * @param ftpDirEnum    ftp服务保存目录
     * @param strings           字符串
     * @return
     */
    public static List<String> uploadStringFile(FtpDirEnum ftpDirEnum, List<String> strings){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = ftpDirEnum.getPath();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                //循环上传
                int index = 0;
                int size = strings.size();
                for (int i = 0; i < size; i++) {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path+directory+fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(strings.get(i).getBytes()));
                        StaticLog.error("FTP上传={}=文件:{} 剩余：{}", fileName, b, (size-(i+1)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }



    /**
     * 单个上传文件
     * @param ftpDirEnum    ftp服务保存目录
     * @param inputStream       输入文件流
     * @return
     */
    public static String uploadStreamFile(FtpDirEnum ftpDirEnum, InputStream inputStream){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        String directory = ftpDirEnum.getPath();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //设置缓存大小
            ftpClient.setBufferSize(1024);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            StaticLog.error("切换至FTP目录:{}",directory);
            if(ftpClient.changeWorkingDirectory(directory)){
                String fileName = createFileName(null);
                try {

                    boolean b = ftpClient.storeFile(fileName, inputStream);
                    StaticLog.error("FTP上传文件:{}",b);
                    return FtpConfig.path+directory+fileName;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return null;
    }

    /**
     * 批量上传文件
     * @param ftpDirEnum    ftp服务保存目录
     * @param inputStreams      输入文件流
     * @return
     */
    public static List<String> uploadStreamFile(FtpDirEnum ftpDirEnum, List<InputStream> inputStreams){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = ftpDirEnum.getPath();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                int size = inputStreams.size();
                for (int i = 0; i < size; i++) {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path + directory + fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, inputStreams.get(i));
                        StaticLog.error("FTP上传={}=文件:{} 剩余：{}", fileName, b, (size-(i+1)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }



    /**
     * 批量上传文件
     * @param ftpDirEnum    ftp服务保存目录
     * @param bytes             字节流
     * @return
     */
    public static List<String> uploadByteFile(FtpDirEnum ftpDirEnum, List<byte[]> bytes){
        FTPClient ftpClient = FtpConfig.getFtpClient();
        List<String> paths = new ArrayList<>();
        String directory = ftpDirEnum.getPath();
        try {
            //设置文件类型
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            //创建目录
            ftpClient.makeDirectory(directory);
            //切换至FTP目录
            if(ftpClient.changeWorkingDirectory(directory)){
                StaticLog.error("切换至FTP目录:{}",directory);
                //循环上传
                bytes.forEach(by -> {
                    String fileName = createFileName(null);
                    paths.add(FtpConfig.path+directory+fileName);
                    try {
                        boolean b = ftpClient.storeFile(fileName, new ByteArrayInputStream(by));
                        StaticLog.error("FTP上传={}=文件:{}",fileName,b);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                StaticLog.error("切换目录失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            offFtp(ftpClient);
        }
        return paths;
    }

    /**
     * 销毁FTP的对象
     * @param ftpClient
     */
    public static void offFtp(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     * @param filePath  文件路径
     * @return
     * @throws IOException
     */
    public static boolean delFile(String filePath) throws IOException {
        FTPClient ftpClient = FtpConfig.getFtpClient();
        if (ObjectUtil.isNotNull(filePath)){
            filePath = StrUtil.removeAll(filePath, FtpConfig.path);
            boolean deleteFile = ftpClient.deleteFile(filePath);
            StaticLog.error("删除文件：{}",deleteFile);
            return deleteFile;
        }
        offFtp(ftpClient);
        return false;
    }

    /**
     * 创建文件名称
     * @return
     */
    private static String createFileName(String name){
        return DateUtil.currentSeconds() + RandomUtil.randomNumbers(8)+(name!=null?name:"");
    }

    /**
     * 根据地址获得数据的输入流
     *
     * @param strUrl
     *            网络连接地址
     * @return
     */
    public static InputStream getImageStreamByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(60 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            return inStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除文件
     * @param articleImg
     */
    public static void delFile(List<String> articleImg) {
        FTPClient ftpClient = FtpConfig.getFtpClient();
        int size = articleImg.size();
        if (size>0){
            for (int i = 0; i < size; i++) {
                String img = StrUtil.removeAll(articleImg.get(i), FtpConfig.path);
                try {
                    boolean deleteFile = ftpClient.deleteFile(img);
                    StaticLog.error("FTP删除文件 总数:{} 剩余：{} 状态：{}", size, (size-(i+1)), deleteFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        offFtp(ftpClient);
    }

    public static String getFileSuffix(String fileWholeName) {
        if (StrUtil.isEmpty(fileWholeName)) {
            return "none";
        } else {
            int lastIndexOf = fileWholeName.lastIndexOf(".");
            return fileWholeName.substring(lastIndexOf + 1);
        }
    }
}
