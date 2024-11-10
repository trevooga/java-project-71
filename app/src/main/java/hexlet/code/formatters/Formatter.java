package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, Status> differences,
                                String formatName) throws Exception {
        switch (formatName.toLowerCase()) {
            case "plain":
                return Plain.generate(differences);
            case "json":
                return Json.generate(differences);
            case "stylish":
                return Stylish.generate(differences);
            default:
                throw new IllegalArgumentException("Unknown format: " + formatName);
        }
    }
}
