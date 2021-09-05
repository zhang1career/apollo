package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantObject extends Instant<Object> {
    @NotNull
    @Contract("_ -> new")
    static public InstantObject of(Object value) {
        return new InstantObject(value);
    }

    private InstantObject(Object value) {
        super(ApolloType.INSTANT_OBJECT, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Object> o) {
        if (!(o instanceof InstantObject)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantObject is needed");
        }
        return Integer.compare(hashCode(), o.hashCode());
    }
}
