package com.raymond.quickstart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * command line key-value parse scenario:
 * <p>
 * 1. -- could be parsed by spring {@link Environment}, must be declared after *.jar
 * eg: java -jar *.jar --key1=value1
 * <p>
 * 2. -D could be parse by spring {@link Environment} & {@link System}, must be declared before *.jar
 * eg: java -jar -Dkey1=value1 *.jar
 *
 * @author raymondmuzzi
 * @since 2023-10-29 12:36:27
 */
@Configuration
public class JvmAndEnvProperty implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JvmAndEnvProperty.class);

    private final Environment environment;

    public JvmAndEnvProperty(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        // jvm
        String systemProp = "my.system.prop";
        String systemProperty = System.getProperty(systemProp);

        // system env cannot parse it
        String envProp = "my.env.prop";
        String envProperty = System.getenv().get(envProp);

        // spring
        String springEnvProperty = environment.getProperty(envProp);
        String springSystemProperty = environment.getProperty(systemProp);

        LOGGER.info("System config prop: {}, value is: {}", systemProp, systemProperty);
        LOGGER.info("Env config prop: {}, value is: {}", envProp, envProperty);
        LOGGER.info("SpringEnv config prop: {}, value is: {}", envProp, springEnvProperty);
        LOGGER.info("SpringSystem config prop: {}, value is: {}", systemProp, springSystemProperty);
        LOGGER.debug("Test debug level log.......");
        LOGGER.warn("Test warn level log.......");


    }
}
