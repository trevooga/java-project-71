package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class Parsing {
    public static TreeMap<String, Object> parse(File jsonfile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> unsortedMap = objectMapper.readValue(jsonfile, new TypeReference<>() {
        });
        return new TreeMap<>(unsortedMap);
    }
}
