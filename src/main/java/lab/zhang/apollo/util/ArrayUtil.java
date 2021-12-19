package lab.zhang.apollo.util;

/**
 * @author zhangrj
 */
public class ArrayUtil {
    static public boolean isNill(Object[] arr) {
        return arr == null || arr.length <= 0;
    }

    public static Object[] parseArray(Object obj) {
        if (obj.getClass().isArray()) {
            return (Object[]) obj;
        }
        throw new RuntimeException("The param type is wrong");
    }

    private ArrayUtil() {
        throw new AssertionError();
    }
}
