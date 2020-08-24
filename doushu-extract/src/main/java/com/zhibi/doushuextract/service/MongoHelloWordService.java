package com.zhibi.doushuextract.service;

import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.util.PageUtils;

import java.util.List;

/**
 * MangoDb  crud  demo ！
 * @author liuzhen.tian
 * @version 1.0 MongoHelloWordService.java  2020/7/31 16:31
 */
public interface MongoHelloWordService {
    /**
     * 分页查找
     * @return
     */
    PageUtils getListByPage();

    /**
     * 模糊查询
     * @return
     */
    List<MongoEntity> getListByKeyWord(MongoEntity entity);


    /**
     * 多条件查询
     * @return
     */
    List<MongoEntity> getListByKeyWord();

    /**
     * 批量插入
     */
    void insertByList();

    /**
     * 批量修改
     */
    void updateByList();
}
