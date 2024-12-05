package com.aaa.schedule.quartz.config;

/**
 * @author liuzhen.tian
 * @version 1.0 DataSourceConfig.java  2024/12/5 21:16
 */

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * https://www.cnblogs.com/bzq-nancy/p/13930880.html
 *
 * @see org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    /**
     * 数据源配置的前缀，必须与application.properties中配置的对应数据源的前缀一致
     */
    private static final String BUSINESS_DATASOURCE_PREFIX = "spring.datasource.biz";

    private static final String QUARTZ_DATASOURCE_PREFIX = "spring.datasource.quartz";

    @Primary
    @Bean
    @ConfigurationProperties(prefix = BUSINESS_DATASOURCE_PREFIX)
    public DataSource businessDataSource() {
        return new HikariDataSource();
    }

    /**
     * @QuartzDataSource 注解则是配置Quartz独立数据源的配置
     * 这里必须要加 @QuartzDataSource，否则，quartzDataSource.getIfAvailable();拿不到数据，JdbcStoreTypeConfiguration.quartzDataSourceInitializer 引用时保持对齐
     */
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = QUARTZ_DATASOURCE_PREFIX)
    public DataSource quartzDataSource() {
        return new HikariDataSource();
    }
}
