package com.hyfly.dolphinscheduler.sdk.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * json utils based on jackson
 */
public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * Json string deserialize to Object.
     *
     * @param inputStream   json string input stream
     * @param typeReference {@link Type} of object
     * @param <T>           General type
     * @return object
     * @throws RuntimeException if deserialize failed
     */
    public static <T> T parseObject(InputStream inputStream, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }
}
