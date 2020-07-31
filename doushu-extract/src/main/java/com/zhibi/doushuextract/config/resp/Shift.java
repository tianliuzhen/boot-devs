package com.zhibi.doushuextract.config.resp;

/**
 * 断言
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */

import com.zhibi.doushuextract.domain.enums.ResultCode;

/**
 * @author soul
 */
public final class Shift {

    private Shift() {
    }

    /**
     * 抛出具体的{@code RestStatus}异常
     *
     * @param resultCode  自定义异常实体
     * @param details 额外添加至details字段中的任意实体, 最终会被解析成JSON
     */
    public static void fatal(ResultCode resultCode, Object... details) {

        throw new DemoException(resultCode.getMessage(),resultCode.getCode());
    }

}
