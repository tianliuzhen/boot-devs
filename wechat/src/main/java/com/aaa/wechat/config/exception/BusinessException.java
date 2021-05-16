package com.aaa.wechat.config.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class BusinessException extends RuntimeException {
    @Getter
    @Setter
    private String returnCode;


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public BusinessException(String returnCode) {
        super();
        this.returnCode = returnCode;
    }


}
