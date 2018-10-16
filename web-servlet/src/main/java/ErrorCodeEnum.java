public enum ErrorCodeEnum {
    parameter_error(10001, "参数错误"),
    password_error(10002, "密码错误");

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
