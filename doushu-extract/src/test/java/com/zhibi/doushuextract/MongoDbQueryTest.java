package com.zhibi.doushuextract;

import com.zhibi.doushuextract.dao.MongoHelloWordDao;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
