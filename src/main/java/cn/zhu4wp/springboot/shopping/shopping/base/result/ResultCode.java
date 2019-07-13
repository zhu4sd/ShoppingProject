package cn.zhu4wp.springboot.shopping.shopping.base.result;

public enum ResultCode {
    //登陆成功
    SUCCESS(200,"成功"),
    //登陆失败
    FAIL(500,"失败"),
    //账号/密码出错
    USER_LOGIN_ERROR(500201,"账号/密码输入错误"),
    //账号已被注册
    USER_HAS_EXISTED(500202,"该用户名已被占用"),
    //未登录/登陆失效
    USER_NOT_LOGIN(500203,"未登录/登陆失效，请重新登陆");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
