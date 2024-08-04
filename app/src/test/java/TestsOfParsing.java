import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestsOfParsing {

    @Test
    public void parsingTest() throws IOException {
        File file1 = new File("src/test/resources/file1.json");
        File file2 = new File("src/test/resources/file2.json");
        System.out.println(Differ.generate(file1, file2));
    }
}
