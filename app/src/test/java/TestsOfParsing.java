import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsOfParsing {

    @Test
    public void parsingTest() throws IOException {
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
}
