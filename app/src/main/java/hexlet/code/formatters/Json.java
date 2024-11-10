package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Json {
    public static String generate(Map diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
