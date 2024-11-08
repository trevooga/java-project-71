package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.Map;

public class Stylish {
    public static String generate(Map<String, Status> differences) throws Exception {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");

        for (Map.Entry<String, Status> status : differences.entrySet()) {
            String key = status.getKey();
            Status value = status.getValue();
            switch (value.getStatusName()) {
                case Status.ADDED:
                    differenceOfFiles.append("  + ").append(key).append(": ")
                            .append(value.getNewValue()).append("\n");
                    break;
                case Status.DELETED:
                    differenceOfFiles.append("  - ").append(key).append(": ")
                            .append(value.getOldValue()).append("\n");
                    break;
                case Status.CHANGED:
                    differenceOfFiles.append("  - ").append(key).append(": ")
                            .append(value.getOldValue()).append("\n");
                    differenceOfFiles.append("  + ").append(key).append(": ")
                            .append(value.getNewValue()).append("\n");
                    break;
                case Status.UNCHANGED:
                    differenceOfFiles.append("    ").append(key).append(": ")
                            .append(value.getOldValue()).append("\n");
                    break;
                default:
                    throw new Exception("Unidentified key" + value.getStatusName());
            }
        }

        return differenceOfFiles.append("}").toString();
    }
}
