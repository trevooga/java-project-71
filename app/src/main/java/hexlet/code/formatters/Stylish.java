package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;

public class Stylish {
    public static String generate(List<Status> differences) throws Exception {
        StringBuilder differenceOfFiles = new StringBuilder("{\n");

        for (Status status : differences) {
            String key = (String) status.getKey(); // Получаем ключ
            switch (status.getStatusName()) {
                case Status.ADDED:
                    differenceOfFiles.append("  + ").append(key).append(": ")
                            .append(status.getNewValue()).append("\n");
                    break;
                case Status.DELETED:
                    differenceOfFiles.append("  - ").append(key).append(": ")
                            .append(status.getOldValue()).append("\n");
                    break;
                case Status.CHANGED:
                    differenceOfFiles.append("  - ").append(key).append(": ")
                            .append(status.getOldValue()).append("\n");
                    differenceOfFiles.append("  + ").append(key).append(": ")
                            .append(status.getNewValue()).append("\n");
                    break;
                case Status.UNCHANGED:
                    differenceOfFiles.append("    ").append(key).append(": ")
                            .append(status.getOldValue()).append("\n");
                    break;
                default:
                    throw new Exception("Unidentified key");
            }
        }

        return differenceOfFiles.append("}").toString();
    }
}
