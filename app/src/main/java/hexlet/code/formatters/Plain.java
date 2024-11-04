package hexlet.code.formatters;

import hexlet.code.Status;

import java.util.List;

public class Plain {
    public static String generate(List<Status> differences) throws Exception {
        StringBuilder differenceOfFiles = new StringBuilder();

        for (Status status : differences) {
            String key = (String) status.getKey(); // Получаем ключ
            switch (status.getStatusName()) {
                case Status.ADDED:
                    differenceOfFiles.append("Property '").append(key).append("' was added with value: ")
                            .append(processComplexValue(status.getNewValue())).append("\n");
                    break;
                case Status.DELETED:
                    differenceOfFiles.append("Property '").append(key).append("' was removed\n");
                    break;
                case Status.CHANGED:
                    differenceOfFiles.append("Property '").append(key).append("' was updated. From ")
                            .append(processComplexValue(status.getOldValue())).append(" to ")
                            .append(processComplexValue(status.getNewValue())).append("\n");
                    break;
                case Status.UNCHANGED:
                    break;
                default:
                    throw new Exception("Unidentified key");
            }
        }

        return differenceOfFiles.toString().trim();
    }

    public static String processComplexValue(Object value) {
        if (value != null && (value.toString().contains("{") || value.toString().contains("["))) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value == null ? "null" : value.toString();
    }
}
