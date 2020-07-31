package com.zhibi.doushuextract.repository;

import com.zhibi.doushuextract.domain.entity.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author liuzhen.tian
 * @version 1.0 HelloWordRepository.java  2020/7/31 16:36
 */
@Repository
public interface HelloWordRepository extends MongoRepository<MongoEntity,String> {
}
