package lab.zhang.apollo.pojo.operand.instant;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operand.Instant;
import lab.zhang.apollo.pojo.operand.variable.VariableArray;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantArray extends Instant<Object[]> {
    @NotNull
    @Contract("_ -> new")
    static public InstantArray of(Object obj) {
        Object[] value = parseArray(obj);
        return new InstantArray(value);
    }

    private InstantArray(Object[] value) {
        super(ApolloType.INSTANT_ARRAY, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Object[]> o) {
        if (!(o instanceof InstantArray) && !(o instanceof VariableArray)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantArray/VariableArray is needed");
        }
        if ((o instanceof VariableArray)) {
            return 1;
        }
        return Integer.compare(hashCode(), o.hashCode());
    }
}
