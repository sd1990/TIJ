package org.songdan.tij.designpattern.composite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.base.Strings;

public class JsonUtil {

    public static ObjectMapper jsonMapper = new ObjectMapper() {
        {
            configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false) // 默认为true
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false) // 默认为true
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 默认为true
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_COMMENTS, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true) // 默认为false
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) // 默认为false
                    .configure(JsonParser.Feature.IGNORE_UNDEFINED, true) // 默认为false
                    .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    };

    private JsonUtil() {
    }

    public static String of(Object o) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(o);
    }

    @SuppressWarnings("unchecked")
    public static <T> T of(String json, Class<T> tClass) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        return jsonMapper.readValue(json, tClass);
    }

    public static <T> T of(String json, TypeReference<T> reference) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        return jsonMapper.readerFor(reference).readValue(json);
    }

    public static <T> T of(String json, Class<T> clazz, Module module) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        jsonMapper.registerModule(module);
        return jsonMapper.readerFor(clazz).readValue(json);
    }

    public static <T> List<T> ofList(String json, Class<T> tClass) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        JavaType javaType = jsonMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,
                tClass);
        return jsonMapper.readValue(json, javaType);
    }

    public static <K, V> Map<K, V> ofMap(String json, Class<K> keyClass, Class<V> valueClass) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        MapType mapType = jsonMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
        return jsonMapper.readValue(json, mapType);
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        if (null == obj) {
            return null;
        }
        return jsonMapper.writeValueAsString(obj);
    }
}
