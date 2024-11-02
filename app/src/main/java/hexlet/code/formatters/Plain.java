package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public class Plain {
    public static String generate(Map<String, Map<String, Object>> differences) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder();

        if (differences.containsKey("ADD")) {
            for (Map.Entry<String, Object> entry : differences.get("ADD").entrySet()) {
                differenceOfFiles.append("Property '" + entry.getKey() + "' was added with value: " + processComplexValue(entry.getValue()) + "\n");
            }
        }

        if (differences.containsKey("DELETE")) {
            for (Map.Entry<String, Object> entry : differences.get("DELETE").entrySet()) {
                differenceOfFiles.append("Property '" + entry.getKey() + "' was removed\n");
            }
        }

        if (differences.containsKey("NOTCHANGED")) {
            for (Map.Entry<String, Object> entry : differences.get("NOTCHANGED").entrySet()) {
                // Сравниваем старое и новое значение
                Object oldValue = entry.getValue();
                Object newValue = differences.get("ADD").get(entry.getKey());
                if (newValue != null && !oldValue.equals(newValue)) {
                    differenceOfFiles.append("Property '" + entry.getKey() + "' was updated. From "
                            + processComplexValue(oldValue) + " to " + processComplexValue(newValue) + "\n");
                }
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