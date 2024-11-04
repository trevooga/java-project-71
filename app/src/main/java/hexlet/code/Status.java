package hexlet.code;

public final class Status {

    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String statusName;
    private Object oldValue;
    private Object newValue;
    private Object key;
    Status(String statusname, Object key, Object oldvalue, Object newvalue) {
        this.key = key;
        this.statusName = statusname;
        this.oldValue = oldvalue;
        this.newValue = newvalue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Object getKey() {
        return key;
    }
}
