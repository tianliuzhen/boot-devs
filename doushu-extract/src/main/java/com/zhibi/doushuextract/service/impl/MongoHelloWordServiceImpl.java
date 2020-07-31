package com.zhibi.doushuextract.service.impl;

import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import com.zhibi.doushuextract.util.MongoUtil;
import com.zhibi.doushuextract.util.PageUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 MongoHelloWordServiceImpl.java  2020/7/31 16:38
 */
@Service
public class MongoHelloWordServiceImpl implements MongoHelloWordService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private HelloWordRepository helloWordRepository;

    @Resource
    private MongoUtil mongoUtil;

    @Override
    public PageUtils getListByPage() {

        // Query query = new Query(Criteria.where("students.name").is("name1").and("project").is("project1"));
        Query query = new Query(new Criteria());
        query.with(Sort.by(Sort.Direction.ASC, "id"));
        /**
         *  currentPage =100  pageSize=10
         *  这里表示 取  900 - 999 即是  901 - 1000
         *  表示（取每页为10条的数据的，第 100 页数据）
         */
        mongoUtil.start(100, 10, query);
        List<MongoEntity> teachers = mongoTemplate.find(query, MongoEntity.class);
        long count = mongoTemplate.count(query, MongoEntity.class);
        PageUtils pageUtils = mongoUtil.PageUtils(count, teachers);

        return pageUtils;
    }

    @Override
    public List<MongoEntity> getListByKeyWord(MongoEntity entity) {
        return null;
    }

    @Override
    public List<MongoEntity> getListByKeyWord() {
        return null;
    }



}
