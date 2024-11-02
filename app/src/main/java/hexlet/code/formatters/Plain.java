package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Plain {
    public static String generate(Map<String, Map<String, Object>> differences,
                                  TreeSet<String> allKeys) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder();

        for (String key : allKeys) {
            boolean isAdded = differences.get("ADD") != null && differences.get("ADD").containsKey(key);
            boolean isDeleted = differences.get("DELETE") != null && differences.get("DELETE").containsKey(key);
            boolean isNotChanged = differences.get("NOTCHANGED") != null
                    && differences.get("NOTCHANGED").containsKey(key);

            if (isAdded && isDeleted) {
                differenceOfFiles.append("Property '").append(key).append("' was updated. From ")
                        .append(processComplexValue(differences.get("DELETE").get(key))).append(" to ")
                        .append(processComplexValue(differences.get("ADD").get(key))).append("\n");
            } else if (isAdded) {
                differenceOfFiles.append("Property '").append(key).append("' was added with value: ")
                        .append(processComplexValue(differences.get("ADD").get(key))).append("\n");
            } else if (isDeleted) {
                differenceOfFiles.append("Property '").append(key).append("' was removed\n");
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
