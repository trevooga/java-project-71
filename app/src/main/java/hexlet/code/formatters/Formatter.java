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
            default:
                return Stylish.generate(map1, map2);
        }
    }
}
