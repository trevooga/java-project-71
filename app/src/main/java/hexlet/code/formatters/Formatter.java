package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> mapOfDiff ,
                                String formatName) throws IOException {
        switch (formatName) {
            case "plain":
                return Plain.generate(mapOfDiff);
            case "json":
                return Json.jsonGenerate(mapOfDiff);
            case "stylish":
                return Stylish.generate(mapOfDiff);
            default:
                throw new IllegalArgumentException("This style is not supported: " + formatName);
        }
    }
}