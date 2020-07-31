package com.zhibi.doushuextract.config.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoException  extends RuntimeException {
    private String errorMsg;

    private int errorCode;
}

