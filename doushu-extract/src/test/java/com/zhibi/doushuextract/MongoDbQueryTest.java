package com.zhibi.doushuextract;

import com.alibaba.fastjson.JSON;
import com.zhibi.doushuextract.dao.MongoHelloWordDao;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 MongoDbQueryTest.java  2020/7/31 18:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDbQueryTest {
    @Autowired
    private MongoHelloWordService mongoHelloWordService;

    @Autowired
    private HelloWordRepository helloWordRepository;

    @Autowired
    private MongoHelloWordDao mongoHelloWordDao;

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 分页、排序、查询
     */
    @Test
    public void getPageList(){
        mongoHelloWordService.getListByPage();


    }

    @Test
    public void getListJoin() {
        // LookupOperation lookupToLots = LookupOperation.newLookup().
        //         from("MongoV2Entity").//关联表名 lots
        //         localField("_id").//关联字段
        //         foreignField("_id").//主表关联字段对应的次表字段
        //         as("groups");//查询结果集合名
        //
        // //主表
        // AggregationOperation match = Aggregation.match(new Criteria());
        // //次表
        // AggregationOperation match1 = Aggregation.match(new Criteria());
        //
        // UnwindOperation unwind = Aggregation.unwind("groups");
        // Aggregation aggregation = Aggregation.newAggregation(match, match1, lookupToLots, unwind);
        //
        // mongoTemplate.aggregate(aggregation, "groupsEntity", GroupsEntity.class).getMappedResults();
    }

    @Test
    public void twoTableQuery() {
        LookupOperation lookupOperation=LookupOperation.newLookup().
                from("mongoV2Entity").  //关联从表名
                localField("id").     //主表关联字段
                foreignField("id").//从表关联的字段
                as("result");   //查询结果名
        AggregationOperation match = Aggregation.match(new Criteria());
        Aggregation aggregation=Aggregation.newAggregation(match, lookupOperation); //多条件
        // UnwindOperation unwind = Aggregation.unwind("result");
        List<Map> results = mongoTemplate.aggregate(aggregation,"mongoEntity", Map.class).getMappedResults();
        //上面的mongoEntity必须是查询的主表名
        System.out.println(results.toString());
    }

    /**
     * 注意: 这里不区分大小写
     * 模糊查找
     */
    @Test
    public void getPageLike(){

        //完全匹配
        Pattern pattern = Pattern.compile("^小明$", Pattern.CASE_INSENSITIVE);
        //右匹配
        // Pattern pattern = Pattern.compile("^.*小明$", Pattern.CASE_INSENSITIVE);
        //左匹配
        // Pattern pattern = Pattern.compile("^小明.*$", Pattern.CASE_INSENSITIVE);
        //模糊匹配
        // Pattern pattern = Pattern.compile("^.*alEn.*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("name").regex(pattern));
        //模糊查找的列名
        List<MongoEntity> users = mongoTemplate.find(query, MongoEntity.class);
    }
}
