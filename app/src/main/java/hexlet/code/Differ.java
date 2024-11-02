package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(String file1, String file2, String formatName) throws IOException {
        String extension = getFileExtension(file1);

        String contentFile1 = new String(Files.readAllBytes(Paths.get(file1)));
        String contentFile2 = new String(Files.readAllBytes(Paths.get(file2)));
        Map<String, Object> mapOfFile1;
        Map<String, Object> mapOfFile2;

        if (extension.equals("json")) {
            mapOfFile1 = Parser.jsonMap(contentFile1);
            mapOfFile2 = Parser.jsonMap(contentFile2);
        } else {
            mapOfFile1 = Parser.yamlMap(contentFile1);
            mapOfFile2 = Parser.yamlMap(contentFile2);
        }

        TreeSet<String> allKeys = new TreeSet<>(mapOfFile1.keySet());
        allKeys.addAll(mapOfFile2.keySet());

        return Formatter.format(DifferenceFinder.Difference(mapOfFile1, mapOfFile2, allKeys), formatName, allKeys);
    }

    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "stylish");
    }

    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf(".");
        if (lastIndexOfDot > 0 && lastIndexOfDot < filePath.length() - 1) {
            return filePath.substring(lastIndexOfDot + 1).toLowerCase();
        }
        return "";
    }
}