package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, Status> differences,
                                String formatName) throws Exception {
        switch (formatName.toLowerCase()) {
            case "plain":
                return Plain.generate(differences);
            case "json":
                List<Map<String, Object>> diffList = new ArrayList<>();
                for (Map.Entry<String, Status> entry : differences.entrySet()) {
                    Map<String, Object> diffEntry = new LinkedHashMap<>();
                    diffEntry.put("key", entry.getKey());
                    diffEntry.put("status", entry.getValue());
                    diffList.add(diffEntry);
                }
                return Json.jsonGenerate(diffList);
            case "stylish":
                return Stylish.generate(differences);
            default:
                throw new IllegalArgumentException("Unknown format: " + formatName);
        }
    }
}
