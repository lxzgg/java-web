class ErrorException extends RuntimeException {
    ErrorException(String message) {
        this.code = -1;
        this.message = message;
    }

    ErrorException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

    ErrorException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}