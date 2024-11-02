package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public class Stylish {
    public static String generate(Map<String, Map<String, Object>> differences) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");

        if (differences.containsKey("ADD")) {
            for (Map.Entry<String, Object> entry : differences.get("ADD").entrySet()) {
                differenceOfFiles.append("  + " + entry.getKey() + ": " + entry.getValue()).append("\n");
            }
        }

        if (differences.containsKey("DELETE")) {
            for (Map.Entry<String, Object> entry : differences.get("DELETE").entrySet()) {
                differenceOfFiles.append("  - " + entry.getKey() + ": " + entry.getValue()).append("\n");
            }
        }

        if (differences.containsKey("NOTCHANGED")) {
            for (Map.Entry<String, Object> entry : differences.get("NOTCHANGED").entrySet()) {
                differenceOfFiles.append("    " + entry.getKey() + ": " + entry.getValue()).append("\n");
            }
        }

        return differenceOfFiles.append("}").toString();
    }
}