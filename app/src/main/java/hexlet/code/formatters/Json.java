package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Json {
    public static String jsonGenerate(Map<String, Map<String, Object>> differences,
                                      TreeSet<String> allKeys) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        for (String key : allKeys) {
            if (differences.get("ADD") != null && differences.get("ADD").containsKey(key)) {
                resultMap.put("+ " + key, differences.get("ADD").get(key));
            } else if (differences.get("DELETE") != null && differences.get("DELETE").containsKey(key)) {
                resultMap.put("- " + key, differences.get("DELETE").get(key));
            } else if (differences.get("NOTCHANGED") != null && differences.get("NOTCHANGED").containsKey(key)) {
                resultMap.put("  " + key, differences.get("NOTCHANGED").get(key));
            }
        }
        return new ObjectMapper().writeValueAsString(resultMap);
    }
}
