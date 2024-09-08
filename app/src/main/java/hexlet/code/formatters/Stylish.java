package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

import static hexlet.code.utils.Proccessing.removeValue;
import static hexlet.code.utils.Proccessing.updateValue;
import static hexlet.code.utils.Proccessing.addValue;

public class Stylish {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");

        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 == null || value2 == null) {
                    if (value1 == null && value2 != null) {
                        differenceOfFiles.append(removeValue(key, value1)).append("\n");

                        differenceOfFiles.append(updateValue(key, value2)).append("\n");
                    } else if (value1 != null && value2 == null) {
                        differenceOfFiles.append(removeValue(key, value1)).append("\n");
                        differenceOfFiles.append(updateValue(key, value2)).append("\n");
                    } else {
                        differenceOfFiles.append(addValue(key, value2)).append("\n");
                    }
                } else {
                    if (!value1.equals(value2)) {
                        differenceOfFiles.append(removeValue(key, value1)).append("\n");
                        differenceOfFiles.append(updateValue(key, value2)).append("\n");
                    } else {
                        differenceOfFiles.append(addValue(key, value2)).append("\n");
                    }
                }
            } else if (map1.containsKey(key)) {
                differenceOfFiles.append(removeValue(key, value1)).append("\n");
            } else {
                differenceOfFiles.append(updateValue(key, value2)).append("\n");
            }
        }
        return differenceOfFiles + "}";
    }
}
