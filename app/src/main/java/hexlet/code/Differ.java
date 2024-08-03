package hexlet.code;


import com.sun.source.tree.Tree;
import utils.Parsing;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Differ {
    public static String generate(File file1, File file2) {
        String differenceOfFiles = "{\n";
        try {
            Map<String, Object> mapOfFile1 = Parsing.parse(file1);
            Map<String, Object> mapOfFile2 = Parsing.parse(file2);

            for (Map.Entry<String, Object> entry1 : mapOfFile1.entrySet()) { // Проходим по элементам первого файла
                String key1 = entry1.getKey();
                Object value1 = entry1.getValue();

                if (mapOfFile2.containsKey(key1)) {
                    Object value2 = mapOfFile2.get(key1);
                    if (!value1.equals(value2)) {
                        differenceOfFiles += ("- " + key1 + ": " + value1 + "\n");
                        differenceOfFiles += ("+ " + key1 + ": " + value2 + "\n");
                    } else {
                        differenceOfFiles += ("  " + key1 + ": " + value2 + "\n");
                    }
                } else {
                    differenceOfFiles += ("- " + key1 + ": " + value1 + "\n");
                }
            }

            for (Map.Entry<String, Object> entry2 : mapOfFile2.entrySet()) { // Проходим по элементам второго файла, которых нет в первом
                String key2 = entry2.getKey();
                if (!mapOfFile1.containsKey(key2)) {
                    differenceOfFiles += ("+ " + key2 + ": " + entry2.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differenceOfFiles + "}";
    }
}
