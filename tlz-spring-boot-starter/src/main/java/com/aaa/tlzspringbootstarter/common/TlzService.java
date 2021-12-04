package com.aaa.tlzspringbootstarter.common;

/**
 * 自动注入TlzService测试类
 *
 * @author liuzhen.tian
 * @version 1.0 TlzService.java  2021/12/4 10:59
 */

public class TlzService {

    /**
     * @param object
     * @param <T>
     * @return
     */
    public <T> T getBySelf(T object) {
        return object;
    }
}
