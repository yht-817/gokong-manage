package cn.gokong.www.gokongmain.exception;

/**
 * 说明:参数校验异常处理类
 *
 * @author ideac
 * @CreateDate 2018/9/9 19:21
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class ParamValidException extends RuntimeException{
    /**
     * 异常代码
     */
    private int code;
    /**
     * 异常消息
     */
    private String message;

    public ParamValidException(String message) {
        this.message = message;
    }

    public ParamValidException(int code, String message) {
        this.code = code;
        this.message = message;
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
