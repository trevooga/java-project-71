package hexlet.code;


import utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Differ {
    public static String generate(File file1, File file2) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");
        Map<String, Object> mapOfFile1 = Parser.parse(file1);
        Map<String, Object> mapOfFile2 = Parser.parse(file2);

        for (Map.Entry<String, Object> entry1 : mapOfFile1.entrySet()) { // Проходим по элементам первого файла
            String key1 = entry1.getKey();
            Object value1 = entry1.getValue();

            if (mapOfFile2.containsKey(key1)) {
                Object value2 = mapOfFile2.get(key1);
                if (!value1.equals(value2)) {
                    differenceOfFiles.append("- ").append(key1).append(": ").append(value1).append("\n");
                    differenceOfFiles.append("+ ").append(key1).append(": ").append(value2).append("\n");
                } else {
                    differenceOfFiles.append("  ").append(key1).append(": ").append(value2).append("\n");
                }
            } else {
                differenceOfFiles.append("- ").append(key1).append(": ").append(value1).append("\n");
            }
        }

        for (Map.Entry<String, Object> entry2 : mapOfFile2.entrySet()) { // Проходим по элементам второго файла
            String key2 = entry2.getKey();                                             //, которых нет в первом
            if (!mapOfFile1.containsKey(key2)) {
                differenceOfFiles.append("+ ").append(key2).append(": ").append(entry2.getValue()).append("\n");
            }
        }

        return differenceOfFiles + "}";
    }
}
