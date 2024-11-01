package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;


public class Plain {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder();
        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            String processedValue1 = processComplexValue(value1);
            String processedValue2 = processComplexValue(value2);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 == null && value2 == null) {
                    continue;
                } else if (value1 == null) {
                    differenceOfFiles.append("Property '" + key + "' was added with value: " + processedValue2 + "\n");
                } else if (value2 == null) {
                    differenceOfFiles.append("Property '" + key + "' was removed\n");
                } else if (!value1.equals(value2)) {
                    differenceOfFiles.append("Property '" + key + "' was updated. From "
                            + processedValue1 + " to " + processedValue2 + "\n");
                }
            } else if (map1.containsKey(key)) {
                differenceOfFiles.append("Property '" + key + "' was removed\n");
            } else {
                differenceOfFiles.append("Property '" + key + "' was added with value: " + processedValue2 + "\n");
            }
        }
        return differenceOfFiles.toString().trim();
    }
    public static String processComplexValue(Object value) {
        if (value != null && (value.toString().contains("{") || value.toString().contains("["))) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value == null ? "null" : value.toString();
    }
}