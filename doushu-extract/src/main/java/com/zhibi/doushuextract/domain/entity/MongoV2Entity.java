package com.zhibi.doushuextract.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhen.tian
 * @version 1.0 HelloWordEntity.java  2020/7/31 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MongoV2Entity {
    private Long id;
    private String name;
    private Integer sex;
    private Integer age;
    /**
     * 假设 mongoEntity 的 ID
     */
    private Long mongoEntity;

}
