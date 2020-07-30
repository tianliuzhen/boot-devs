package com.aaa.sass.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuzhen.tian
 * @version 1.0 ResultBO.java  2020/7/30 21:26
 */
@Data
public class ResultResp<T> {


    @ApiModelProperty(value = "响应状态码",example = "200")
    @JsonProperty("code")
    private int code = Constants.DEFAULT_OK;

    @ApiModelProperty(value = "响应消息",example = "success")
    @JsonProperty("message")
    private String message = Constants.DEFAULT_SUCCESS;

    private T result;

    public ResultResp(T result) {
        this.result = result;
    }
}
