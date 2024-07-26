package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


public class WatchTheFile {
    public static Map<String, Object> parse(File jsonfile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonfile, new TypeReference<>() {
        });
    }
    }
