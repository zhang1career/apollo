package lab.zhang.apollo.util;

/**
 * @author zhangrj
 */
public class StrUtil {

    static public boolean isNill(String str) {
        return str == null || str.isEmpty() || "".equals(str.trim());
    }
}
