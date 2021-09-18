package lab.zhang.apollo.util;

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
}
