package hexlet.code;


import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DifferenceFinder {
    public static Map<String, Status> difference(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        Map<String, Status> difference = new LinkedHashMap<>();
        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                difference.put(key, new Status(Status.ADDED, null, value2)); //ADDED
            } else if (!map2.containsKey(key)) {
                difference.put(key, new Status(Status.DELETED, value1, null)); //DELETED
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                difference.put(key, new Status(Status.UNCHANGED, value1, value2)); //UNCHANGED
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                difference.put(key, new Status(Status.CHANGED, value1, value2)); //CHANGED
            }
        }
        return difference;
    }
}
