package com.zhibi.doushuextract.dao;

import com.mongodb.client.result.UpdateResult;
import com.zhibi.doushuextract.domain.entity.MongoEntity;
import com.zhibi.doushuextract.domain.entity.MongoV2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 MongoHelloWordDao.java  2020/7/31 15:59
 */
@Repository
public class MongoHelloWordDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建
     */
    public void insert(MongoEntity mongoEntity){
        mongoTemplate.save(mongoEntity);
    }

    /**
     * 创建
     */
    public void insertV2(MongoV2Entity mongoEntity){
        mongoTemplate.save(mongoEntity);
    }


    /**
     * 更新
     */
    public void update(MongoEntity entity){
        Query query = new Query(Criteria.where("id").is(entity.getId()));

        Update update= new Update().set("age", entity.getAge()).set("name", entity.getName());
        //更新查询返回结果集的第一条
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, MongoEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,MongoEntity.class);
    }

    /**
     * 删除
     */
    public void delete(Long id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoEntity.class);
    }

    /**
     *查询
     */
    public MongoEntity getMongoEntity(MongoEntity mongoEntity){
        Query query = new Query(Criteria.where("name").is(mongoEntity.getName()));
        MongoEntity one = mongoTemplate.findOne(query, MongoEntity.class);
        return one;
    }


}
