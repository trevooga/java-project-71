package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Formatter {

    public static String format(Map<String, Map<String, Object>> differences, String formatName, TreeSet<String> allKeys) throws IOException {
        switch (formatName.toLowerCase()) {
            case "plain":
                return Plain.generate(differences, allKeys);
            case "json":
                return Json.jsonGenerate(differences, allKeys);
            case "stylish":
                return Stylish.generate(differences, allKeys);
            default:
                throw new IllegalArgumentException("Unknown format: " + formatName);
        }
    }
}