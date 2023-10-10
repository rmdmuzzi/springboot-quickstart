package com.raymond.quickstart.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author raymondmuzzi
 * @since 2023-10-10 20:54:28
 */
@Component
public class StartupRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(dataSource.getConnection().getMetaData().getURL());
    }
}
