package com.xuecheng.base.exception;

public enum ExceptionEnum {
    UNKNOWN_ERR("未知错误");
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    ExceptionEnum(String errMsg) {
        this.errMsg = errMsg;
    }
}
