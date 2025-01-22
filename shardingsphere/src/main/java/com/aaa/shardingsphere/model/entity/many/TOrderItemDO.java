package com.aaa.shardingsphere.model.entity.many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author liuzhen.tian
 * @version 1.0 TOrderDO.java  2025/1/22 22:21
 */
@Table(name = "t_order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TOrderItemDO {
    private Long orderId;
    private Long orderItemId;
    private String orderItemName;
}
