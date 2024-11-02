package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DifferenceFinder {
    public static Map<String, Map<String, Object>> Difference(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        Map<String, Map<String, Object>> difference = new HashMap<>();

        // Инициализация карт для добавленных, удаленных и неизмененных
        difference.put("ADD", new HashMap<>());
        difference.put("DELETE", new HashMap<>());
        difference.put("NOTCHANGED", new HashMap<>());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 == null && value2 == null) {
                    continue; // оба значения null
                } else if (value1 == null) {
                    difference.get("ADD").put(key, value2);
                } else if (value2 == null) {
                    difference.get("DELETE").put(key, value1);
                } else if (!value1.equals(value2)) {
                    difference.get("DELETE").put(key, value1);
                    difference.get("ADD").put(key, value2);
                } else {
                    difference.get("NOTCHANGED").put(key, value1); // или value2
                }
            } else if (map1.containsKey(key)) {
                difference.get("DELETE").put(key, value1);
            } else {
                difference.get("ADD").put(key, value2);
            }
        }
        return difference;
    }
}