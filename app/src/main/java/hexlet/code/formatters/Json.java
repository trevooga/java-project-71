package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Json {
    public static String jsonGenerate(Map<String, Map<String, Object>> differences) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();

        if (differences.containsKey("ADD")) {
            for (Map.Entry<String, Object> entry : differences.get("ADD").entrySet()) {
                resultMap.put("+ " + entry.getKey(), entry.getValue());
            }
        }

        if (differences.containsKey("DELETE")) {
            for (Map.Entry<String, Object> entry : differences.get("DELETE").entrySet()) {
                resultMap.put("- " + entry.getKey(), entry.getValue());
            }
        }

        if (differences.containsKey("NOTCHANGED")) {
            for (Map.Entry<String, Object> entry : differences.get("NOTCHANGED").entrySet()) {
                resultMap.put("  " + entry.getKey(), entry.getValue());
            }
        }

        return new ObjectMapper().writeValueAsString(resultMap);
    }
}