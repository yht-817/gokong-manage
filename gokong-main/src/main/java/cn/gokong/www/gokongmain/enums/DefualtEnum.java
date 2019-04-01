package cn.gokong.www.gokongmain.enums;


import cn.gokong.www.gokongmain.ftp.FtpConfig;

/**
 * 说明:文件属性
 *
 * @author Mick
 * @CreateDate 2018/6/23 12:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum DefualtEnum {

    CODE_10010001(FtpConfig.path+"img/defualt/userhead","默认头像"),
    CODE_10010002(FtpConfig.path+"img/defualt/article","默认资讯图片"),
    ;

    private String url;
    private String desc;

    DefualtEnum(String dir, String desc) {
        this.url = dir;
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
