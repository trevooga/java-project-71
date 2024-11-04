package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;

public class Formatter {

    public static String format(List<Status> differences,
                                String formatName) throws Exception {
        switch (formatName.toLowerCase()) {
            case "plain":
                return Plain.generate(differences);
            case "json":
                return Json.jsonGenerate(differences);
            case "stylish":
                return Stylish.generate(differences);
            default:
                throw new IllegalArgumentException("Unknown format: " + formatName);
        }
    }
}
