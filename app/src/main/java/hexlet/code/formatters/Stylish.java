package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Stylish {
    public static String generate(Map<String, Map<String, Object>> differences, TreeSet<String> allKeys) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");

        for (String key : allKeys) {
            boolean isAdded = differences.get("ADD") != null && differences.get("ADD").containsKey(key);
            boolean isDeleted = differences.get("DELETE") != null && differences.get("DELETE").containsKey(key);
            boolean isNotChanged = differences.get("NOTCHANGED") != null && differences.get("NOTCHANGED").containsKey(key);

            if (isAdded && isDeleted) {
                differenceOfFiles.append("  - ").append(key).append(": ")
                        .append((differences.get("DELETE").get(key))).append("\n");
                differenceOfFiles.append("  + ").append(key).append(": ")
                        .append((differences.get("ADD").get(key))).append("\n");
            } else if (isAdded) {
                differenceOfFiles.append("  + ").append(key).append(": ")
                        .append((differences.get("ADD").get(key))).append("\n");
            } else if (isDeleted) {
                differenceOfFiles.append("  - ").append(key).append(": ")
                        .append((differences.get("DELETE").get(key))).append("\n");
            } else if (isNotChanged) {
                differenceOfFiles.append("    ").append(key).append(": ")
                        .append((differences.get("NOTCHANGED").get(key))).append("\n");
            }
        }

        return differenceOfFiles.append("}").toString();
    }
}