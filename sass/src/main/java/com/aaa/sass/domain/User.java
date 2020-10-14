package com.aaa.sass.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 User.java  2020/10/14 13:54
 */
@Data
public class User {
    private static final long serialVersionUID = -656178832709238674L;
    /**
     *[出参格式化][入参格式化]  jackson注解，用于返回格式化后的字符串，这里设置仅是局部剩生效
     * 作用：
     *     1、后台的时间 格式化 发送到前台
     *     2、接受前台的时间格式 传到后台的格式
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    /**
     *[fastJson 序列格式化] fastJson注解，解决 fastJson 序列化问题，若不加该注解默认序列化为 13位时间戳
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
