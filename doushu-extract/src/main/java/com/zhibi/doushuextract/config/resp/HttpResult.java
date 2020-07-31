
package com.zhibi.doushuextract.config.resp;

import com.zhibi.doushuextract.domain.enums.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 全局自定义返回包装器
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */
@Data
@NoArgsConstructor
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;
    private boolean success;
    private T data;
    private Integer code=200;
    private String message;

    private HttpResult(boolean success, T data, Integer code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    private HttpResult(boolean success, T data, ResultCode resultCode) {
        this.success = success;
        this.data = data;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


/**
     * 成功返回
     */

    public static <T> HttpResult<T> ok(T data) {
        return new HttpResult<>(Boolean.TRUE, data, ResultCode.SUCCESS);
    }


     /**
     * 异常返回-指定错误码
     */

    public static HttpResult fail(ResultCode resultCode) {
        return new HttpResult<>(Boolean.FALSE, null, resultCode);
    }


/**
     * 异常返回-非指定异常
     */

    public static HttpResult fail(Integer code, String message) {
        return new HttpResult<>(Boolean.FALSE, null, code, message);
    }

    //getter and setter
}

