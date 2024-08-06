package hexlet.code;

import utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(File file1, File file2) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");
        Map<String, Object> mapOfFile1 = Parser.parse(file1);
        Map<String, Object> mapOfFile2 = Parser.parse(file2);

        TreeSet<String> allKeys = new TreeSet<>(mapOfFile1.keySet());
        allKeys.addAll(mapOfFile2.keySet());

        for (String key : allKeys) {
            Object value1 = mapOfFile1.get(key);
            Object value2 = mapOfFile2.get(key);

            if (mapOfFile1.containsKey(key) && mapOfFile2.containsKey(key)) {
                if (value1 == null || value2 == null) {
                    if (value1 == null && value2 != null) {
                        differenceOfFiles.append("- ").append(key).append(": ").append(value1).append("\n");
                        differenceOfFiles.append("+ ").append(key).append(": ").append(value2).append("\n");
                    } else if (value1 != null && value2 == null) {
                        differenceOfFiles.append("- ").append(key).append(": ").append(value1).append("\n");
                        differenceOfFiles.append("+ ").append(key).append(": ").append(value2).append("\n");
                    } else {
                        differenceOfFiles.append("  ").append(key).append(": ").append(value2).append("\n");
                    }
                } else {
                    if (!value1.equals(value2)) {
                        differenceOfFiles.append("- ").append(key).append(": ").append(value1).append("\n");
                        differenceOfFiles.append("+ ").append(key).append(": ").append(value2).append("\n");
                    } else {
                        differenceOfFiles.append("  ").append(key).append(": ").append(value2).append("\n");
                    }
                }
            } else if (mapOfFile1.containsKey(key)) {
                differenceOfFiles.append("- ").append(key).append(": ").append(value1).append("\n");
            } else {
                differenceOfFiles.append("+ ").append(key).append(": ").append(value2).append("\n");
            }
        }

        return differenceOfFiles + "}";
    }
}
