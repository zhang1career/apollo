package lab.zhang.apollo.util;

/**
 * @author zhangrj
 */
public class CastUtil {
    @SuppressWarnings("unchecked")
    public static <T> T from(Object obj) {
        return (T) obj;
    }

    private CastUtil() {
        throw new AssertionError();
    }
}
