package com.aaa.shardingsphere.web;

import com.aaa.shardingsphere.dal.mapper.many.TOrderItemMapper;
import com.aaa.shardingsphere.dal.mapper.many.TOrderMapper;
import com.aaa.shardingsphere.dal.mapper.single.TAddressMapper;
import com.aaa.shardingsphere.model.entity.many.TOrderDO;
import com.aaa.shardingsphere.model.entity.many.TOrderItemDO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhen.tian
 * @version 1.0 ShardingController.java  2025/1/22 22:28
 */
@RestController
@RequiredArgsConstructor
public class ShardingController {
    private final TOrderItemMapper tOrderItemMapper;
    private final TOrderMapper tOrderMapper;
    private final TAddressMapper tAddressMapper;

    @GetMapping("insertOrder")
    private void insertOrder(@RequestParam(defaultValue = "1") Long userId) {
        long orderId = Long.parseLong(1L + "" + userId);
        tOrderMapper.insert(new TOrderDO(orderId, "订单1", userId));

        for (long i = 0; i < 3; i++) {
            tOrderItemMapper.insert(new TOrderItemDO(orderId, i, "订单明细" + i));
        }
    }
}
