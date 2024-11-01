package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class Parser {
    public static Map<String, Object> yamlMap(String text) throws IOException {
        ObjectMapper yamlMapper = new YAMLMapper();
        Map<String, Object> yamlMap = yamlMapper.readValue(text, new TypeReference<>() {
        });
        return yamlMap;
    }

    public static Map<String, Object> jsonMap(String text) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> unsortedMap = objectMapper.readValue(text, new TypeReference<>() {
        });
        return unsortedMap;
    }
}
