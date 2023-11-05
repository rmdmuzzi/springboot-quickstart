package com.raymond.quickstart;

import com.raymond.quickstart.config.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application entrance
 *
 * @author raymondmuzzi
 * @since 2023-10-09 19:35:55
 */
@EnableConfigurationProperties(ServerConfig.class)
@SpringBootApplication
public class SpringbootQuickstartApplication {

    static {
        // Set system property
        System.setProperty("spring.devtools.restart.enabled", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickstartApplication.class, args);
    }

}
