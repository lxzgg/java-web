package com.web.utils;

/**
 * 自定义错误异常
 */
public class ErrorException extends RuntimeException {

    /**
     * 错误码枚举
     */
    public enum ErrorEnum {
        parameter_error(10001, "参数错误"),
        password_error(10002, "密码错误");

        ErrorEnum(Integer code, String message) {
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

    /**
     * @param message 错误提示
     */
    public ErrorException(String message) {
        this.code = -1;
        this.message = message;
    }

    /**
     * @param code    错误码
     * @param message 错误提示
     */
    public ErrorException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @param errorCodeEnum 错误码枚举
     */
    public ErrorException(ErrorEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}