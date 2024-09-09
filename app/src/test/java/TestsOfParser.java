import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import hexlet.code.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestsOfParser {

    @Test
    void generateTest() throws IOException {
        String correctFile = "{\n"
                +
                "  - follow: false\n"
                +
                "    host: hexlet.io\n"
                +
                "  - proxy: 123.234.53.22\n"
                +
                "  - timeout: 50\n"
                +
                "  + timeout: 20\n"
                +
                "  + verbose: true\n"
                +
                "}";
        String file1 = "src/test/resources/JSONfiles/file1.json";
        String file2 = "src/test/resources/JSONfiles/file2.json";
        assertEquals(correctFile, Differ.generate(file1, file2, "stylish"));
    }

    @Test
    void parsingTest() throws IOException {
        TreeMap correctMap = new TreeMap<String, Object>();
        final int digit = 50;
        correctMap.put("host", "hexlet.io");
        correctMap.put("timeout", digit);
        correctMap.put("proxy", "123.234.53.22");
        correctMap.put("follow", false);


        assertEquals(correctMap, Parser.parse(new File("src/test/resources/YAMLfiles/file1.yaml")));
    }

    @Test
    void testGenerateDiff() {
        String file1 = "src/test/resources/JSONfiles/file1.json";
        String file2 = "src/test/resources/JSONfiles/file2.json";

        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute("-f", "stylish", file1, file2);

        try {
            String result = Differ.generate(file1, file2, "stylish");
            assertNotNull(result);
            System.out.println(result);
        } catch (IOException e) {
            fail("IOException was thrown");
        }
    }

    @Test
    void testYamlGenerateDiff() {
        String file1 = "src/test/resources/YAMLfiles/file1.yaml";
        String file2 = "src/test/resources/YAMLfiles/file2.yaml";

        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute("-f", "stylish", file1, file2);

        try {
            String result = Differ.generate(file1, file2, "stylish");
            assertNotNull(result);
        } catch (IOException e) {
            fail("IOException was thrown");
        }
    }

    @Test
    void testOfInsertedFiles() throws IOException {
        String correctAnswer = "{\n"
                +
                "    chars1: [a, b, c]\n"
                +
                "  - chars2: [d, e, f]\n"
                +
                "  + chars2: false\n"
                +
                "  - checked: false\n"
                +
                "  + checked: true\n"
                +
                "  - default: null\n"
                +
                "  + default: [value1, value2]\n"
                +
                "  - id: 45\n"
                +
                "  + id: null\n"
                +
                "  - key1: value1\n"
                +
                "  + key2: value2\n"
                +
                "    numbers1: [1, 2, 3, 4]\n"
                +
                "  - numbers2: [2, 3, 4, 5]\n"
                +
                "  + numbers2: [22, 33, 44, 55]\n"
                +
                "  - numbers3: [3, 4, 5]\n"
                +
                "  + numbers4: [4, 5, 6]\n"
                +
                "  + obj1: {nestedKey=value, isNested=true}\n"
                +
                "  - setting1: Some value\n"
                +
                "  + setting1: Another value\n"
                +
                "  - setting2: 200\n"
                +
                "  + setting2: 300\n"
                +
                "  - setting3: true\n"
                +
                "  + setting3: none\n"
                +
                "}";
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(correctAnswer, Differ.generate(file1, file2, "stylish"));
    }

    @Test
    void plainTester() throws IOException {
        String answer = "Property 'chars2' was updated. From [complex value] to false\n"
                +
                "Property 'checked' was updated. From false to true\n"
                +
                "Property 'default' was updated. From null to [complex value]\n"
                +
                "Property 'id' was updated. From 45 to null\n"
                +
                "Property 'key1' was removed\n"
                +
                "Property 'key2' was added with value: 'value2'\n"
                +
                "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                +
                "Property 'numbers3' was removed\n"
                +
                "Property 'numbers4' was added with value: [complex value]\n"
                +
                "Property 'obj1' was added with value: [complex value]\n"
                +
                "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                +
                "Property 'setting2' was updated. From 200 to 300\n"
                +
                "Property 'setting3' was updated. From true to 'none'";
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2, "plain"), answer);
    }

    @Test
    void jsonTest() throws IOException {
        String expected = "{\"+ numbers4\":[4,5,6],\"- setting2\":200,\"- setting1\":\"Some value\",\"" +
                "- default\":null,\"+ numbers2\":[22,33,44,55],\"- setting3\":true,\"+ setting1\"" +
                ":\"Another value\",\"+ checked\":true,\"+ setting2\":300,\"+ key2\":\"value2\",\"" +
                "+ setting3\":\"none\",\"- checked\":false,\"+ id\":null,\"  numbers1\":[1,2,3,4],\"" +
                "- obj1\":null,\"  chars1\":[\"a\",\"b\",\"c\"],\"+ default\":[\"value1\",\"value2\"],\"" +
                "- numbers4\":null,\"+ obj1\":{\"nestedKey\":\"value\",\"isNested\":true},\"- key2\":null,\"" +
                "- numbers3\":[3,4,5],\"- id\":45,\"- key1\":\"value1\",\"- chars2\":[\"d\",\"e\",\"f\"],\"" +
                "+ chars2\":false,\"- numbers2\":[2,3,4,5]}";
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2, "json"), expected);
    }
}
