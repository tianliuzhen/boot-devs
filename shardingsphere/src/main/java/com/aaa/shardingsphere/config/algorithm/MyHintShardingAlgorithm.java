package com.aaa.shardingsphere.config.algorithm;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * @author liuzhen.tian
 * @version 1.0 MyHintShardingAlgorithm.java  2025/2/5 21:18
 */
public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    /**
     * @param collection        表名[ds_0, ds_1]或库名[t_order_0, t_order_1]
     * @param hintShardingValue 命中表名或者库名值
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        Collection<Long> values = hintShardingValue.getValues();
        // todo 这里写死只会路由到 ds_0库和t_order_0表
        return Lists.newArrayList(collection.stream().findFirst().get());
    }
}
