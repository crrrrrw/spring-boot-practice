package com.crw.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@MapperScan(basePackages = "com.crw.mapper")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "sbp.datasource")
    public DataSource datasource() {
        return new DruidDataSource();
    }

}