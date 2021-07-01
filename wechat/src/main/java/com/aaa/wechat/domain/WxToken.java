package com.aaa.wechat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 WxToken.java  2021/7/1 22:43
 */
@Data
public class WxToken {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty (value = "expires_in")
    private Long expiresIn;
}
