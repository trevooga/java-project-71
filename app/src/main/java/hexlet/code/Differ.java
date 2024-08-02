package hexlet.code;


import utils.Parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Differ {
    public static List<String> difference(File file1, File file2) {
        List<String> differenceOfFiles = new ArrayList<>();
        try {
            Map<String, Object> mapToDifference1 = Parsing.parse(file1);
            Map<String, Object> mapToDifference2 = Parsing.parse(file2);

            // Проходим по элементам первого файла
            for (Map.Entry<String, Object> entry1 : mapToDifference1.entrySet()) {
                String key1 = entry1.getKey();
                Object value1 = entry1.getValue();

                if (mapToDifference2.containsKey(key1)) {
                    Object value2 = mapToDifference2.get(key1);
                    if (!value1.equals(value2)) {
                        differenceOfFiles.add("- " + key1 + ": " + value1);
                        differenceOfFiles.add("+ " + key1 + ": " + value2);
                    } else {
                        differenceOfFiles.add(key1 + ": " + value2);
                    }
                } else {
                    differenceOfFiles.add("- " + key1 + ": " + value1);
                }
            }

            // Проходим по элементам второго файла, которых нет в первом
            for (Map.Entry<String, Object> entry2 : mapToDifference2.entrySet()) {
                String key2 = entry2.getKey();
                if (!mapToDifference1.containsKey(key2)) {
                    differenceOfFiles.add("+ " + key2 + ": " + entry2.getValue());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        return differenceOfFiles.stream().sorted().toList();
    }
}
