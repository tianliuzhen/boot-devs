package com.aaa.wechat.service.impl;

import com.aaa.wechat.api.WechatSdkRepository;
import com.aaa.wechat.config.SiteConfig;
import com.aaa.wechat.domain.DecryptSpec;
import com.aaa.wechat.domain.WechatResponse;
import com.aaa.wechat.service.WeChatService;
import com.aaa.wechat.utils.AesUtil;
import com.alibaba.fastjson.JSON;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 WeChatServiceImpl.java  2020/8/22 15:54
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Resource
    private WechatSdkRepository wechatSdkRepository;
    @Autowired
    private SiteConfig siteConfig;


    @Override
    public String decrypt(DecryptSpec decryptSpec) {

        String wechatOpen = wechatSdkRepository.getOpenid(siteConfig.getAppid(), siteConfig.getSecret(), decryptSpec.getCode(), siteConfig.getGrantType());
        WechatResponse wechatResponse = JSON.parseObject(wechatOpen, WechatResponse.class);

        wechatResponse.getSessionKey();
        String result = "";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData =
                "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" +
                        "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS" +
                        "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" +
                        "3hVbJSRgv+4lGOETKUQz6OYStslQ142d" +
                        "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" +
                        "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6" +
                        "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" +
                        "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn" +
                        "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" +
                        "8LOddcQhULW4ucetDf96JcR3g0gfRK4P" +
                        "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" +
                        "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
                        "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" +
                        "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV" +
                        "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" +
                        "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" +
                        "Db/XcxxmK01EpqOyuxINew==";
        String iv="r7BXXKkLb8qrSNn05n0qiA==";
        try {
            decryptSpec.getEncryptedData();
            decryptSpec.getIv();
            result = AesUtil.decryptWXAppletInfo(sessionKey, encryptedData, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

      /*  String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData =
                "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" +
                        "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS" +
                        "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" +
                        "3hVbJSRgv+4lGOETKUQz6OYStslQ142d" +
                        "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" +
                        "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6" +
                        "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" +
                        "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn" +
                        "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" +
                        "8LOddcQhULW4ucetDf96JcR3g0gfRK4P" +
                        "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" +
                        "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
                        "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" +
                        "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV" +
                        "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" +
                        "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" +
                        "Db/XcxxmK01EpqOyuxINew==";
        String iv="r7BXXKkLb8qrSNn05n0qiA==";
*/
}
