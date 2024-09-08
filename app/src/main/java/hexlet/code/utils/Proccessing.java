package hexlet.code.utils;

public class Proccessing {
    public static String processComplexValue(Object value) {
        if (value != null && (value.toString().contains("{") || value.toString().contains("["))) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value == null ? "null" : value.toString();
    }

    public static String updateProp(String key, String value1, String value2) {
        return "Property '" + key + "' was updated. From " + value1 + " to " + value2 + "\n";
    }

    public static String removeProp(String key) {
        return "Property '" + key + "' was removed\n";
    }

    public static String addProp(String key, String value1) {
        return "Property '" + key + "' was added with value: " + value1 + "\n";
    }

    public static String removeValue(String key, Object value) {
        return "- " + key + ": " + value;
    }

    public static String updateValue(String key, Object value) {
        return "+ " + key + ": " + value;
    }

    public static String addValue(String key, Object value) {
        return "  " + key + ": " + value;
    }
}
