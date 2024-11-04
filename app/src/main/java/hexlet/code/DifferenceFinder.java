package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Objects;

public class DifferenceFinder {
    public static List<Status> difference(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeys = getAllKeys(map1, map2);

        List<Status> statuses = new ArrayList<>();
        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (value1 != null && value2 != null && !value1.equals(value2)) {
                    statuses.add(new Status(Status.CHANGED, key, value1, value2));
                } else if (value1 == null && value2 != null) {
                    statuses.add(new Status(Status.CHANGED, key, null, value2));
                } else if (value1 != null && value2 == null) {
                    statuses.add(new Status(Status.CHANGED, key, value1, null));
                } else if (Objects.equals(value1, value2)) {
                    statuses.add(new Status(Status.UNCHANGED, key, value1, value2));
                }
            } else if (!map1.containsKey(key)) {
                statuses.add(new Status(Status.ADDED, key, null, value2));
            } else if (!map2.containsKey(key)) {
                statuses.add(new Status(Status.DELETED, key, value1, null));
            }
        }
        return statuses;
    }

    public static TreeSet<String> getAllKeys(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        return allKeys;
    }
}
