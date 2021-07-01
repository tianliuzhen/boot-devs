package com.aaa.wechat.web;

import com.aaa.wechat.domain.City;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * openFeign 测试接口
 *
 * @author liuzhen.tian
 * @version 1.0 OpenFeignController.java  2021/7/1 20:56
 */
@RestController
@RequestMapping(value = "openFeignApi/")
public class OpenFeignController {


    /**
     * 表单传参------测试普通表单传参
     *
     * @param id   id
     * @param code code
     */
    @PostMapping("/findByIdAndCode")
    public Map findByIdAndCode(@RequestParam(required = false) Integer id, @RequestParam(required = false) String code) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("id", id);
        hashMap.put("code", code);
        return hashMap;
    }

    /**
     * 表单传参------测试pojo表单传参，注意这里不能带 @RequestParam 注解
     *
     * @param city city
     */
    @GetMapping("/findByCity")
    public String findByCity(City city) {
        System.out.println(city);
        return JSON.toJSONString(city);

    }

    /**
     * 表单传参------测试 map表单传参，注意这里一定要带 @RequestParam 注解，否则无效
     *
     * @param map map
     */
    @GetMapping("/findByMap")
    public Map findByMap(@RequestParam Map map) {
        System.out.println(map);
        return map;

    }

    /**
     * json传参
     *
     * @param city city
     */
    @PostMapping("/findByJson")
    public String findByJson(@RequestBody City city, HttpServletRequest request) {
        System.out.println(city);
        System.out.println(request.getHeader("token"));
        return JSON.toJSONString(city);

    }


}
