package lab.zhang.apollo.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class StrUtil {

    static public boolean isNill(String str) {
        return str == null || str.isEmpty() || "".equals(str.trim());
    }

    @NotNull
    @Contract(pure = true)
    static public String[] explode(@NotNull String str, String seperator) {
        return str.split(seperator);
    }

    static public String implode(Iterable<?> iterable, String separator) {
        return StringUtils.join(iterable, separator);
    }

    static public String parseString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Integer) {
            return Integer.toString((Integer) obj);
        }
        throw new RuntimeException("The param type is wrong");
    }

    private StrUtil() {
        throw new AssertionError();
    }
}
