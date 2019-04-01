package cn.gokong.www.gokongmain.enums;

/**
 * 说明:后台响应枚举
 *
 * @author ikook
 * @CreateDate 2018/9/10 15:11
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public enum ResponseEnum {
    WANT_PHONENO_BIND(10010001,"需要绑定手机号码"),

    SESSION_TIMEOUT(10010002,"会话超时"),
    ;

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
