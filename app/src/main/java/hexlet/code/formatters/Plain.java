package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String generate(Map<String, Status> differences) throws Exception {
        StringBuilder differenceOfFiles = new StringBuilder();
        for (Map.Entry<String, Status> entry : differences.entrySet()) {
            String key = entry.getKey();
            Status value = entry.getValue();
            switch (value.getStatusName()) {
                case Status.ADDED:
                    differenceOfFiles.append("Property '").append(key).append("' was added with value: ")
                            .append(stringify(value.getNewValue())).append("\n");
                    break;
                case Status.DELETED:
                    differenceOfFiles.append("Property '").append(key).append("' was removed\n");
                    break;
                case Status.CHANGED:
                    differenceOfFiles.append("Property '").append(key).append("' was updated. From ")
                            .append(stringify(value.getOldValue())).append(" to ")
                            .append(stringify(value.getNewValue())).append("\n");
                    break;
                case Status.UNCHANGED:
                    break;
                default:
                    throw new Exception("Unidentified key" + value.getStatusName());
            }
        }

        return differenceOfFiles.toString().trim();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}
