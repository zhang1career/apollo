package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableLong;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantLong extends Instant<Long> {
    @NotNull
    @Contract("_ -> new")
    static public InstantLong of(Object obj) {
        long value = parseLong(obj);
        return new InstantLong(value);
    }

    private InstantLong(Long value) {
        super(ApolloType.INSTANT_LONG, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Long> o) {
        if (!(o instanceof InstantLong) && !(o instanceof VariableLong)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantInt/VariableInt is needed");
        }
        if ((o instanceof VariableLong)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null));
    }
}
