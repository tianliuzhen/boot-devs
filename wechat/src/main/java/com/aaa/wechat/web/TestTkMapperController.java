package com.aaa.wechat.web;

import com.aaa.wechat.mapper.EmpTkMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhen.tian
 * @version 1.0 TestTkMapperControlrlt.java  2022/10/26 21:14
 */
@RestController
@RequestMapping(value = "tkMapper")
public class TestTkMapperController {

    @Resource
    private EmpTkMapper empTkMapper;

    @PostMapping("/selectById")
    public Object selectById() {
        // empTkMapper.insert(new Emp(5L, "x", 1L, 1));

        // Emp emp = new Emp();
        // emp.setId(1L);
        // empTkMapper.selectByExample(emp);

        empTkMapper.selectByPrimaryKey(1L);
        return null;
    }
}
