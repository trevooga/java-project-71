package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Status;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static String jsonGenerate(List<Status> differences) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (Status status : differences) {
            String key = (String) status.getKey(); // Получаем ключ
            switch (status.getStatusName()) {
                case Status.ADDED:
                    resultMap.put("+ " + key, status.getNewValue());
                    break;
                case Status.DELETED:
                    resultMap.put("- " + key, status.getOldValue());
                    break;
                case Status.UNCHANGED:
                    resultMap.put("  " + key, status.getOldValue());
                    break;
                case Status.CHANGED:
                    resultMap.put("- " + key, status.getOldValue());
                    resultMap.put("+ " + key, status.getNewValue());
                    break;
                default:
                    throw new Exception("Unidentified key");
            }
        }
        return new ObjectMapper().writeValueAsString(resultMap);
    }
}
