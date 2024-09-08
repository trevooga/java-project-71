package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(String file1, String file2, String formatName) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.parse(new File(file1));
        Map<String, Object> mapOfFile2 = Parser.parse(new File(file2));

        return Formatter.format(mapOfFile1, mapOfFile2, formatName);
    }
    public static String generate(String file1, String file2) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.parse(new File(file1));
        Map<String, Object> mapOfFile2 = Parser.parse(new File(file2));

        return Formatter.format(mapOfFile1, mapOfFile2, "stylish");
    }
}
