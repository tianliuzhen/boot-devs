package com.aaa.wechat.domain;

import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 DecryptSpec.java  2020/8/22 15:54
 */
@Data
public class DecryptSpec {
    private String iv;
    private String encryptedData;
    private String code;
}
