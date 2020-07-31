package com.aaa.sass.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200,"请求成功"),

    SYSTEM_ERROR(500,"请求失败"),

    INVALID_PARAM(5501,"参数未通过@Valid验证异常"),

    MISTYPE_PARAM(5502,"参数格式有误"),

    MISSING_PARAM(5503,"缺少参数"),

    UNSUPPORTED_METHOD(5504,"不支持的请求类型"),

    INVALID_TOKEN(5505,"token无效！"),
    ;

    private int code;
    private String message;
}
