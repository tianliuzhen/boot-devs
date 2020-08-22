package com.aaa.wechat.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
public class WechatResponse implements Serializable {

    private static final long serialVersionUID = -1050830468556818442L;

    @JSONField(name = "session_key")
    private String sessionKey;

    private String openid;

    private String errcode;

    private String errmsg;

    private String hints;
}
