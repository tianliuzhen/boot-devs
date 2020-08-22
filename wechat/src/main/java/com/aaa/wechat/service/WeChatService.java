package com.aaa.wechat.service;

import com.aaa.wechat.domain.DecryptSpec;

/**
 * @author liuzhen.tian
 * @version 1.0 WechatService.java  2020/8/22 15:54
 */
public interface WeChatService {
    /**
     * 接口解密
     *
     * @param decryptSpec
     * @return
     */
    String decrypt(DecryptSpec decryptSpec);
}
