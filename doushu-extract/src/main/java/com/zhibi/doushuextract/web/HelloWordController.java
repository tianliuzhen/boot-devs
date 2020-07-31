package com.zhibi.doushuextract.web;

import com.zhibi.doushuextract.config.resp.Shift;
import com.zhibi.doushuextract.domain.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */
@RestController
@Slf4j
public class HelloWordController {

    @GetMapping("/HelloWord")
    public void  HelloWord(){
        log.info("HelloWord!");
    }
    @GetMapping("/testApiResponses")
    public void testApiResponses(@RequestParam("id") int id){
        if(id>10){
            Shift.fatal(ResultCode.SYSTEM_ERROR);
        }
    }
}
