package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String file1, String file2, String formatName) throws Exception {
        String extension = getFileExtension(file1);

        String contentFile1 = new String(Files.readAllBytes(Paths.get(file1)));
        String contentFile2 = new String(Files.readAllBytes(Paths.get(file2)));

        Map<String, Object> mapOfFile1 = Parser.parse(contentFile1, extension);
        Map<String, Object> mapOfFile2 = Parser.parse(contentFile2, extension);


        return Formatter.format(DifferenceFinder.difference(mapOfFile1, mapOfFile2), formatName);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    private static String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf(".");
        if (lastIndexOfDot > 0 && lastIndexOfDot < filePath.length() - 1) {
            return filePath.substring(lastIndexOfDot + 1).toLowerCase();
        }
        return "";
    }
}
