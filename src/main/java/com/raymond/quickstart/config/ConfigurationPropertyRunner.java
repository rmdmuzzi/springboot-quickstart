package com.raymond.quickstart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Test configuration property binding
 *
 * @author raymondmuzzi
 * @since 2023-11-01 19:54:30
 */
@Component
public class ConfigurationPropertyRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationPropertyRunner.class);

    private final ServerConfig serverConfig;

    private final JdbcTemplate jdbcTemplate;

    public ConfigurationPropertyRunner(ServerConfig serverConfig, JdbcTemplate jdbcTemplate) {
        this.serverConfig = serverConfig;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Value("${servers.ip-address}")
    private String ipAddress;

    /**
     * must be matched specifically
     */
//    @Value("${servers.ipAddress}")
//    private String ipAddress2;
    @Override
    public void run(String... args) {
        LOGGER.info("Test configuration property ----> {}", serverConfig);
        LOGGER.info("Test @Bean configuration property ----> {}, {}",
                jdbcTemplate.getFetchSize(), jdbcTemplate.getQueryTimeout());
        LOGGER.info("IpAddress ---> {}", ipAddress);
        LOGGER.info("Server timeout -> {}", serverConfig);
    }
}
