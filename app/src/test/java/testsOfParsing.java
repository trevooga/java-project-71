import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class testsOfParsing {

    @Test
    public void parsingTest(){
        File file1 = new File("src/main/java/hexlet/code/file1.json");
        File file2 = new File("src/main/java/hexlet/code/file2.json");
        System.out.println(Differ.generate(file1, file2));
    }
}
