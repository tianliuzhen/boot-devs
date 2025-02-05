package com.aaa.shardingsphere.model.entity.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author liuzhen.tian
 * @version 1.0 TConfig.java  2025/1/22 22:46
 */
@Table(name = "t_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TConfigDO {
    private Long id;
    private String type;
    private String name;
}
