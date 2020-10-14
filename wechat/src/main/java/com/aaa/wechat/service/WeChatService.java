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

    /**
     * 注意：这里的 code 需要打断点获取，是未使用的code，
     *      一次code 只能使用一次
     * jcode 获取token
     */
    String getToken(String jCode);
}
