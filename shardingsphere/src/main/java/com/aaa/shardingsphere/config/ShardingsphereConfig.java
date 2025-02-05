package com.aaa.shardingsphere.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;


/**
 * @author liuzhen.tian
 * @version 1.0 ShardingsphereConfig.java  2025/1/22 21:56
 */
@Configuration
public class ShardingsphereConfig {

    @Bean
    public DataSource dataSource() {
        try {
            // URL url = Thread.currentThread().getContextClassLoader().getResource("sjdbc/sharding-databases-tables.yaml");
            // File file = new File(url.getPath());
            File file = ResourceUtils.getFile("classpath:sjdbc/sharding-databases-tables.yaml");
            // File file = ResourceUtils.getFile("classpath:sjdbc/sharding-databases-tables-hint.yaml");
            return YamlShardingSphereDataSourceFactory.createDataSource(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
