package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

import static hexlet.code.utils.Proccessing.addProp;
import static hexlet.code.utils.Proccessing.updateProp;
import static hexlet.code.utils.Proccessing.removeProp;
import static hexlet.code.utils.Proccessing.processComplexValue;

public class Plain {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws IOException {
        StringBuilder differenceOfFiles = new StringBuilder();

        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String processedValue1 = processComplexValue(value1);
            String processedValue2 = processComplexValue(value2);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 == null && value2 != null) {
                    differenceOfFiles.append(updateProp(key, processedValue1, processedValue2));
                } else if (value1 != null && value2 == null) {
                    differenceOfFiles.append(updateProp(key, processedValue1, processedValue2));
                } else if (value1 != null && !value1.equals(value2)) {
                    differenceOfFiles.append(updateProp(key, processedValue1, processedValue2));
                }
            } else if (map1.containsKey(key)) {
                differenceOfFiles.append(removeProp(key));
            } else {
                differenceOfFiles.append(addProp(key, processedValue2));
            }
        }
        return differenceOfFiles.toString().trim();
    }
}

