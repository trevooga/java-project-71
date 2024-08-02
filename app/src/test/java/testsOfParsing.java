import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class testsOfParsing {

    @Test
    public void parsingTest(){
        File file1 = new File("src/main/java/hexlet/code/file1.json");
        File file2 = new File("src/main/java/hexlet/code/file2.json");
        List<String> stringList = Differ.difference(file1, file2);
        String answer = "{\n";
        for (int i = 0 ; i < stringList.size(); i++) {
            if (i == stringList.size() - 1){
                answer += stringList.get(i) + "\n}";
            } else {
                answer += stringList.get(i) + "\n";
            }
        }
        System.out.println(answer);
    }
}
