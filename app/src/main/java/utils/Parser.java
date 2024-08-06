package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class Parser {
    public static TreeMap<String, Object> parse(File file) throws IOException {
        String extension = file.getPath().substring(file.getPath().length() - 4);
        if (!extension.equals("json")) {
            return new TreeMap<>(yamlMap(file));
        } else {
            return new TreeMap<>(jsonMap(file));
        }
    }

    private static Map<String, Object> yamlMap(File file) throws IOException {
        ObjectMapper yamlMapper = new YAMLMapper();
        Map<String, Object> yamlMap = yamlMapper.readValue(file, new TypeReference<>() {
        });
        return yamlMap;
    }

    private static Map<String, Object> jsonMap(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> unsortedMap = objectMapper.readValue(file, new TypeReference<>() {
        });
        return unsortedMap;
    }
}
