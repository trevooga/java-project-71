package hexlet.code.formatters;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String format(Map<String, Object> map1,
                                Map<String, Object> map2,
                                String formatName) throws IOException {
        switch (formatName) {
            case "plain":
                return Plain.generate(map1, map2);
            case "json":
                return Json.jsonGenerate(map1, map2);
            case "stylish":
                return Stylish.generate(map1, map2);
            default:
                throw new IllegalArgumentException("This style is not supported: " + formatName);
        }
    }
}