package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(File file1, File file2, String formatName) throws IOException {
        Map<String, Object> mapOfFile1 = Parser.parse(file1);
        Map<String, Object> mapOfFile2 = Parser.parse(file2);

        return Formatter.format(mapOfFile1, mapOfFile2, formatName);
    }
}
