package com.zhibi.doushuextract;

import com.zhibi.doushuextract.dao.MongoHelloWordDao;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.domain.entity.MongoV2Entity;
import com.zhibi.doushuextract.repository.HelloWordRepository;
import com.zhibi.doushuextract.service.MongoHelloWordService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liuzhen.tian
 * @version 1.0 MongDbCrudTest.java  2020/7/31 17:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDbCrudTest {

    @Autowired
    private MongoHelloWordService mongoHelloWordService;

    @Autowired
    private HelloWordRepository helloWordRepository;

    @Autowired
    private MongoHelloWordDao mongoHelloWordDao;

    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void insertBatch(){
        List<MongoEntity> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add( MongoEntity.builder().id((long) i).name("cat").age(11).sex(1).build());
        }
        helloWordRepository.saveAll(list);
    }

    @Test
    public void insertBatchV2(){
        for (int i = 0; i < 10; i++) {
            mongoHelloWordDao.insertV2( MongoV2Entity.builder().id((long) i).name("allen").age(11).sex(1).build());
        }

    }

    @Test
    public void testInsert(){
        MongoEntity tom = MongoEntity.builder().id(2L).name("tom").sex(99).build();
        // mongoHelloWordDao.insert(tom);
        helloWordRepository.save(tom);
    }

    @Test
    public void testGetOne(){
        MongoEntity tom = MongoEntity.builder().id(1L).name("tom").sex(99).build();
        mongoHelloWordDao.getMongoEntity(tom);
    }

    @Test
    public void testUpdate(){
        MongoEntity tom = MongoEntity.builder().id(1L).name("tom").sex(19).build();
        mongoHelloWordDao.update(tom);
    }

    @Test
    public void testDelete(){
        mongoHelloWordDao.delete(1L);
    }
}
