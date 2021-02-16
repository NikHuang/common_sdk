package com.hhq.common_sdk.result.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("200","操作成功"),
    SERVER_ERROR("500","系统异常")
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
