package lab.zhang.apollo.util;

/**
 * @author zhangrj
 */
public class NumberUtil {
    public static int parseInteger(Object obj) {
        if (obj instanceof Integer) {
            return (int) obj;
        }
        if (obj instanceof Long) {
            return (int) obj;
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        throw new RuntimeException("The param type is wrong");
    }

    public static long parseLong(Object obj) {
        if (obj instanceof Integer) {
            return (long) obj;
        }
        if (obj instanceof Long) {
            return (long) obj;
        }
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        throw new RuntimeException("The param type is wrong");
    }

    private NumberUtil() {
        throw new AssertionError();
    }
}
