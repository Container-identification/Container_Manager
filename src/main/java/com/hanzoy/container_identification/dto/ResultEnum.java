package com.hanzoy.container_identification.dto;

public enum ResultEnum {
    SUCCESS("0000", "请求成功"),
    PASSWORDERROR("1000", "用户密码错误"),
    TOKENERROR("1001", "token错误"),
    FAILED("1002", "操作失败"),
    ERROR("2000", "用户操作异常"),
    VALIDATEFAILED("2001", "表单参数错误");
    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
