package cn.gokong.www.gokongmain.ftp;

/**
 * 说明:
 *
 * @author Mick
 * @CreateDate 2018/7/26 11:43
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum FtpDirEnum {
    CODE_100010001("/html/article/", "资讯文件存放地址"),
    CODE_100010002("/img/article/", "资讯图片存放地址"),
    CODE_100010003("/img/userhead/", "头像存放地址"),
    CODE_100010004("/img/userback/", "背景图存放地址"),
    CODE_100010005("/img/useriden/", "证件存放地址"),
    CODE_100010006("/img/share/", "用户分享的图片，会进行删除，除了分享不要传"),
    CODE_100010007("/img/circle/", "华人圈图片"),
    CODE_100010008("/img/cityActivity/", "同城活动图片"),
    CODE_100010009("/img/idle/", "二手交易图片"),
    CODE_100010010("/img/logo/", "公司logo"),
    ;
    private String path;
    private String msg;

    FtpDirEnum(String path, String msg) {
        this.path = path;
        this.msg = msg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
