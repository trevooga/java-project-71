package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parse(String text, String formatName) throws Exception {
        switch (formatName) {
            case "json":
                return jsonMap(text);
            case "yml":
            case "yaml":
                return yamlMap(text);
            default:
                throw new Exception("Unknown format: '" + formatName + "'");
        }
    }

    private static Map<String, Object> yamlMap(String text) throws IOException {
        return new YAMLMapper().readValue(text, new TypeReference<>() {
        });
    }

    private static Map<String, Object> jsonMap(String text) throws IOException {
        return new ObjectMapper().readValue(text, new TypeReference<>() {
        });
    }
}
