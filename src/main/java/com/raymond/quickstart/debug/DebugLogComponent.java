package com.raymond.quickstart.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author raymondmuzzi
 * @since 2023-10-10 18:49:19
 */
@Component
public class DebugLogComponent implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugLogComponent.class);

    @Override
    public void run(String... args) {
        LOGGER.debug("-----> 开始执行CommandLineRunner...");
    }
}
