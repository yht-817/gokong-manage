package cn.gokong.www.gokongmain.exception;

import cn.gokong.www.gokongmain.enums.ResponseEnum;

/**
 * 类说明:自定义异常
 *
 * @author ideacoding
 * create 2018-04-11 11:32
 * Email ：ideacoding@163.com
 **/
public class GrilException extends RuntimeException{
    /**
     * 异常代码
     */
    private int code;
    /**
     * 异常消息
     */
    private String message;

    public GrilException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public GrilException(String message) {
        this.code = 500;
        this.message = message;
    }

    public GrilException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
