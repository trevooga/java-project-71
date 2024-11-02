
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsOfParser {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static String yamlResultJson;
    private static String yamlResultPlain;
    private static String yamlResultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }


    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("json_result.json");
        resultPlain = readFixture("plain_result.txt");
        resultStylish = readFixture("stylish_result.txt");
        yamlResultJson = readFixture("yaml_json_result.json");
        yamlResultPlain = readFixture("yaml_plain_result.txt");
        yamlResultStylish = readFixture("yaml_stylish_result.txt");
    }

    @Test
    void defaultTest() throws IOException {
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2), resultStylish);
    }

    @Test
    void stylishTest() throws IOException {
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2), resultStylish);
    }

    @Test
    void plainTester() throws IOException {
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2, "plain"), resultPlain);
    }

    @Test
    void jsonTest() throws IOException {
        String file1 = "src/test/resources/JSONfiles/file3.json";
        String file2 = "src/test/resources/JSONfiles/file4.json";
        assertEquals(Differ.generate(file1, file2, "json"), resultJson);
    }
    @Test
    void jsonYamlTest() throws IOException {
        String file1 = "src/test/resources/YAMLfiles/file1.yaml";
        String file2 = "src/test/resources/YAMLfiles/file2.yaml";
        assertEquals(Differ.generate(file1, file2, "json"), yamlResultJson);
    }

    @Test
    void stylishYamlTest() throws IOException {
        String file1 = "src/test/resources/YAMLfiles/file1.yaml";
        String file2 = "src/test/resources/YAMLfiles/file2.yaml";
        assertEquals(Differ.generate(file1, file2, "stylish"), yamlResultStylish);
    }

    @Test
    void defaultYamlTest() throws IOException {
        String file1 = "src/test/resources/YAMLfiles/file1.yaml";
        String file2 = "src/test/resources/YAMLfiles/file2.yaml";
        assertEquals(Differ.generate(file1, file2), yamlResultStylish);
    }

    @Test
    void plainYamlTest() throws IOException {
        String file1 = "src/test/resources/YAMLfiles/file1.yaml";
        String file2 = "src/test/resources/YAMLfiles/file2.yaml";
        assertEquals(Differ.generate(file1, file2, "plain"), yamlResultPlain);
    }
}
