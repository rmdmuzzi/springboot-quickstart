package com.raymond.quickstart.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json util
 *
 * @author raymondmuzzi
 * @since 2023-10-12 22:17:05
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String getJsonString(T t) {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            LOGGER.error("Parse object as json failed, stack trace is: ", e);
        }
        return null;
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            LOGGER.error("Parse string as object failed, stack trace is: ", e);
        }
        return null;
    }
}
