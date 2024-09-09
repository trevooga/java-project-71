package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static hexlet.code.utils.Proccessing.addValue;
import static hexlet.code.utils.Proccessing.removeValue;
import static hexlet.code.utils.Proccessing.updateValue;

public class Json {
    public static String jsonGenerate(Map<String, Object> map1, Map<String, Object> map2) throws IOException {
        Map<String, Object> map = new HashMap<>();
        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 == null || value2 == null) {
                    updateValue(map, key, value1, value2);
                } else if (!value1.equals(value2)) {
                    updateValue(map, key, value1, value2);
                } else {
                    addValue(map, key, value2);
                }
            } else if (map1.containsKey(key)) {
                removeValue(map, key, value1);
            } else {
                updateValue(map, key, value1, value2);
            }
        }
        return new ObjectMapper().writeValueAsString(map);
    }
}
