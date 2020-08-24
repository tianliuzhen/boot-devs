package com.zhibi.doushuextract.service.impl;

import com.google.common.collect.Lists;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import com.zhibi.doushuextract.util.MongoUtil;
import com.zhibi.doushuextract.util.PageUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
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

    @Override
    public void insertByList() {
        //这里的BulkMode.UNORDERED是个枚举，，，collectionName是mongo的集合名
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,  MongoEntity.class);

        for (int i = 0; i < 10; i++) {
            //注意此处的obj必须是一个DBObject，可以是json对象也可以的bson对象，entity没有试过
            MongoEntity cat = MongoEntity.builder().id((long) i).name("cat").age(11).sex(1).build();
            ops.insert(cat);
        }

        //循环插完以后批量执行提交一下ok！
        ops.execute();
    }



    @Override
    public void updateByList() {

        //BulkMode.UNORDERED:表示并行处理，遇到错误时能继续执行不影响其他操作；
        // BulkMode.ORDERED：表示顺序执行，遇到错误时会停止所有执行
        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, MongoEntity.class);
        List<Pair<Query, Update>> updateList = Lists.newArrayList();
        for (int i = 10; i < 1000; i++) {
            Update update = new Update();
            update.set("age", "new :"+i);
            Query query = new Query(Criteria.where("id").is(i));
            updateList.add(Pair.of(query, update));
        }
        ops.updateOne(updateList);
        ops.execute();
    }
}
