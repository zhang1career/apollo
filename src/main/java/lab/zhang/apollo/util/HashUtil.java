package lab.zhang.apollo.util;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.pojo.ParamContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class HashUtil {
    private static final int PRIME = 997;
    private static final int OFFSET = 7;

    public static int hash(@NotNull Valuable<?> valuable, ParamContext paramContext, int salt) {
        int ret = valuable.hashCode() ^ salt;
        if (paramContext == null) {
            return ret;
        }
        ret += (PRIME + OFFSET) * paramContext.hashCode();

        return ret;
    }

    public static <R, V> int hash(@NotNull Operator<R, V> operator, List<? extends Valuable<V>> operands, int salt) {
        int ret =  operator.hashCode() ^ salt;
        if (operands == null) {
            return ret;
        }
        for (int i = 0; i < operands.size(); i++) {
            int hash = operands.get(i).hashCode();
            ret += (PRIME + OFFSET * i) * hash;
        }

        return ret;
    }

    public static <T> Integer codeFrom(T value) {
        if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            return value.hashCode();
        }
        return null;
    }

    private HashUtil() {
        throw new AssertionError();
    }
}
