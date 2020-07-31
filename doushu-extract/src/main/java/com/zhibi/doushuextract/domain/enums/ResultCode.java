package com.zhibi.doushuextract.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  自定义返回枚举
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    /**
     *
     */
    SUCCESS(200,"请求成功"),

    SYSTEM_ERROR(500,"请求失败"),

    INVALID_PARAM(5501,"参数未通过@Valid验证异常"),

    MISTYPE_PARAM(5502,"参数格式有误"),

    MISSING_PARAM(5503,"缺少参数"),

    UNSUPPORTED_METHOD(5504,"不支持的请求类型"),

    INVALID_TOKEN(5504,"token不能为空"),
    ;

    private int code;
    private String message;
}
