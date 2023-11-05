package com.raymond.quickstart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author raymondmuzzi
 * @since 2023-11-01 20:03:24
 */
@Configuration
public class DsConfig {

    /**
     * Test configuration property on @Bean
     *
     * @param dataSource datasource
     * @return jdbcTemplate
     */
    @Bean
    @ConfigurationProperties(prefix = "jdbc-template-config")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
