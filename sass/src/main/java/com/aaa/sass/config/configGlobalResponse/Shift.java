package com.aaa.sass.config.configGlobalResponse;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/11
 */


import com.aaa.sass.domain.enums.ResultCode;

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
