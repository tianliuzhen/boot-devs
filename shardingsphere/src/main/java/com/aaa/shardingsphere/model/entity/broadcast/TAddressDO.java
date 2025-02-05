package com.aaa.shardingsphere.model.entity.broadcast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author liuzhen.tian
 * @version 1.0 TAddressDO.java  2025/1/22 22:24
 */
@Table(name = "t_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TAddressDO {
    private Long id;
    private Long userId;
    private String address;
}
