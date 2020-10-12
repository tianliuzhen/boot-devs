package com.aaa.sass.annotation;

import java.lang.annotation.*;
/**
 * 无需登录效验
 * @author liuzhen.tian
 * @version 1.0 NonLogin1.java  2020/10/12 17:47
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonLogin {

}
