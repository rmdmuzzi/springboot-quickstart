package com.raymond.quickstart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Test configuration property
 *
 * @author raymondmuzzi
 * @since 2023-11-01 19:52:24
 */
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ipAddress;
    private int port;
    private long timeout;

    /**
     * Test duration, provided by jdk8
     */
    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeout;

    /**
     * Test data size, provided by jdk8
     */
    @DataSizeUnit(DataUnit.KILOBYTES)
    private DataSize myDataSize;
}
