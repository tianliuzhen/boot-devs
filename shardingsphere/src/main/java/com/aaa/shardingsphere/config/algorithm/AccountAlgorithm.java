package com.aaa.shardingsphere.config.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

/**
 * @author liuzhen.tian
 * @version 1.0 AccountAlgorithm.java  2025/2/5 22:01
 */
public class AccountAlgorithm implements StandardShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        long index = shardingValue.getValue() % 2;
        return availableTargetNames.stream()
                .filter(e -> e.endsWith(index + ""))
                .findFirst()
                .orElseThrow(()->new RuntimeException("无法匹配分表index"));
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return null;
    }
}
