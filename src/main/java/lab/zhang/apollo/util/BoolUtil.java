package lab.zhang.apollo.util;

/**
 * @author zhangrj
 */
public class BoolUtil {
    static public boolean parseBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (boolean) obj;
        }
        if (obj instanceof Integer) {
            return ((int) obj) != 0;
        }
        if (obj instanceof String) {
            return Boolean.parseBoolean((String) obj);
        }
        throw new RuntimeException("The param type is wrong");
    }

    private BoolUtil() {
        throw new AssertionError();
    }
}
