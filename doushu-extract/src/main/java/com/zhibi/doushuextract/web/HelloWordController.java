package com.zhibi.doushuextract.web;

import com.zhibi.doushuextract.config.resp.Shift;
import com.zhibi.doushuextract.dao.MongoHelloWordDao;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.domain.enums.ResultCode;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */
@RestController
@Slf4j
@RequestMapping(value = "/helloWord")
public class HelloWordController {

    @GetMapping("/testInfo")
    public void  testInfo(){
        log.info("HelloWord!");
    }
    @GetMapping("/testApiResponses")
    public void testApiResponses(){
        try {
            throw new RuntimeException("测试异常");
        } catch (Exception e) {
            e.printStackTrace();
            Shift.fatal(ResultCode.SYSTEM_ERROR);
        }
    }
}
