package cn.gokong.www.gokongmain.ftp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.setting.dialect.Props;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 说明:FTP配置文件
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public class FtpConfig {

    private static String hostName;

    private static int port;

    private static String userName;

    private static String password;

    public static String path;

    public static String rootDir;

    private static FTPClient ftpClient;

    static {
        Properties prop = Props.getProp("ftp.properties");
        hostName = prop.getProperty("ikeek.ftp.hostname");
        port = Integer.parseInt(prop.getProperty("ikeek.ftp.port"));
        userName = prop.getProperty("ikeek.ftp.username");
        password = prop.getProperty("ikeek.ftp.password");
        path = prop.getProperty("ikeek.ftp.path");
        rootDir = prop.getProperty("ikeek.ftp.rootdir");
    }

    /**
     * 获取FTP实例
     * @return
     */
    public static FTPClient getFtpClient(){
        try {
            ftpClient = new FTPClient();
            //设置编码
            ftpClient.setControlEncoding("utf-8");
            //连接ftp服务器
            ftpClient.connect(hostName, port);
            //登录ftp服务器
            ftpClient.login(userName, password);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            //开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            //设置连接超时1分钟
            ftpClient.setConnectTimeout(60*1000);
            if(!FTPReply.isPositiveCompletion(replyCode)){
                StaticLog.info("连接失败 IP:{} PORT:{}",hostName,port);
            }else {
                StaticLog.info("连接成功 IP:{} PORT:{}",hostName,port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    public static void main(String[] args) throws IOException {
        FTPClient ftpClient = getFtpClient();
        //设置文件类型
        ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
        //设置缓存大小
        ftpClient.setBufferSize(1024);
        if(ftpClient.changeWorkingDirectory("/data/vsftpd/gokong_file/img")){
            boolean storeFile = ftpClient.storeFile("a00b8ebb-93e7-42e3-9a9f-e5679010440d.png", FileUtil.getInputStream(new File("D:/data/vsftpd/gokong_file/img/a00b8ebb-93e7-42e3-9a9f-e5679010440d.png")));
            System.out.println(storeFile);
        }
    }
}
