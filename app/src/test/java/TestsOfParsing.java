import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import utils.Parsing;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class TestsOfParsing {

    @Test
    public void generateTest() throws IOException {
        String correctFile = "{\n"
                +
                "- follow: false\n"
                +
                "  host: hexlet.io\n"
                +
                "- proxy: 123.234.53.22\n"
                +
                "- timeout: 50\n"
                +
                "+ timeout: 20\n"
                +
                "+ verbose: true\n"
                +
                "}";
        File file1 = new File("src/test/resources/file1.json");
        File file2 = new File("src/test/resources/file2.json");
        assertEquals(correctFile, Differ.generate(file1, file2));
    }

    @Test
    public void parsingTest() throws IOException {
        TreeMap correctMap = new TreeMap<String, Object>();
        correctMap.put("host", "hexlet.io");
        correctMap.put("timeout", 50);
        correctMap.put("proxy", "123.234.53.22");
        correctMap.put("follow", false);

        assertEquals(correctMap, Parsing.parse(new File("src/test/resources/file1.json")));
    }

    @Test
    void testGenerateDiff() {
        File file1 = new File("src/test/resources/file1.json");
        File file2 = new File("src/test/resources/file2.json");

        App app = new App();
        CommandLine commandLine = new CommandLine(app);
        commandLine.execute("-f", "stylish", file1.getPath(), file2.getPath());

        try {
            String result = Differ.generate(file1, file2);
            assertNotNull(result);
            System.out.println(result);
        } catch (IOException e) {
            fail("IOException was thrown");
        }
    }
}
