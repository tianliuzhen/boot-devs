package com.aaa.shardingsphere.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * @author liuzhen.tian
 * @version 1.0 ShardingsphereConfig.java  2025/1/22 21:56
 */
@Configuration
public class ShardingsphereConfig {

    @Bean
    public DataSource dataSource() {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("sjdbc/sharding-databases-tables.yaml");
            File file = new File(url.getPath());
            return YamlShardingSphereDataSourceFactory.createDataSource(file);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
