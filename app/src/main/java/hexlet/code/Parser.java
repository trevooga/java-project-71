package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parse(String text, String formatName) throws IOException {
        if (formatName.equals("json")) {
            return jsonMap(text);
        } else {
            return yamlMap(text);
        }
    }

    private static Map<String, Object> yamlMap(String text) throws IOException {
        ObjectMapper yamlMapper = new YAMLMapper();
        Map<String, Object> yamlMap = yamlMapper.readValue(text, new TypeReference<>() {
        });
        return yamlMap;
    }

    private static Map<String, Object> jsonMap(String text) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> unsortedMap = objectMapper.readValue(text, new TypeReference<>() {
        });
        return unsortedMap;
    }
}
