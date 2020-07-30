package com.aaa.sass.domain.base;


/**
 * @author liuzhen.tian
 * @version 1.0 ResultTool.java  2020/7/30 21:26
 */
public class HttpResult<T> {
    public static ResultResp<?> success(Object data) {
        return new ResultResp(data);
    }
}
