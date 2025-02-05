package com.aaa.shardingsphere.web;

import com.aaa.shardingsphere.dal.mapper.broadcast.TAddressMapper;
import com.aaa.shardingsphere.dal.mapper.many.TAccountMapper;
import com.aaa.shardingsphere.dal.mapper.many.TOrderItemMapper;
import com.aaa.shardingsphere.dal.mapper.many.TOrderMapper;
import com.aaa.shardingsphere.dal.mapper.single.TConfigMapper;
import com.aaa.shardingsphere.model.entity.broadcast.TAddressDO;
import com.aaa.shardingsphere.model.entity.many.TAccountDO;
import com.aaa.shardingsphere.model.entity.many.TOrderDO;
import com.aaa.shardingsphere.model.entity.many.TOrderItemDO;
import com.aaa.shardingsphere.model.entity.single.TConfigDO;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.hint.HintManager;
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
    private final TConfigMapper tConfigMapper;
    private final TAccountMapper tAccountMapper;

    /**
     * 插入账户表
     * (自定义分表类)
     */
    @GetMapping("insertAccount")
    private void insertAccount(@RequestParam(defaultValue = "1") Long accountId) {
        tAccountMapper.insert(new TAccountDO(accountId, "账户-" + accountId, 110L));
    }


    /**
     * 插入订单和订单明细关联表（分片表）
     *
     * @param userId
     */
    @GetMapping("insertOrder")
    private void insertOrder(@RequestParam(defaultValue = "1") Long userId) {
        long orderId = Long.parseLong(1L + "" + userId);
        tOrderMapper.insert(new TOrderDO(orderId, "订单1", userId));

        for (long i = 0; i < 3; i++) {
            tOrderItemMapper.insert(new TOrderItemDO(orderId, i, "订单明细" + i));
        }
    }


    /**
     * 插入订单和订单明细关联表（广播表）
     */
    @GetMapping("insertAddress")
    private void insertAddress() {
        tAddressMapper.insert(new TAddressDO(1L, 2L, "浙江杭州西湖"));
    }

    /**
     * 插入订单和订单明细关联表（单表）
     */
    @GetMapping("insertConfig")
    private void insertConfig() {
        tConfigMapper.insert(new TConfigDO(1L, "single", "单表"));
    }


    /**
     * 插入账号表（指定数据库）
     *
     * @param userId
     */
    @GetMapping("insertOrderForHitDb")
    private void insertOrderForHitDb(@RequestParam(defaultValue = "1") Long userId) {
        long orderId = Long.parseLong(1L + "" + userId);
        // 查询条件需要包含分表字段，否则会提示异常： java.sql.SQLException: Not allow DML operation without sharding conditions.
        HintManager instance = HintManager.getInstance();
        // 来添加数据源分片键值
        instance.addDatabaseShardingValue("t_order",0);
        // 来添加表分片键值
        instance.addTableShardingValue("t_order",0);

        tOrderMapper.insert(new TOrderDO(orderId, "订单forHit-" + orderId, userId));

        instance.close();
    }

}
