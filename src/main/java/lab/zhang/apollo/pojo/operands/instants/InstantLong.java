package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableLong;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantLong extends Instant<Long> {

    static private final ApolloType TYPE = ApolloType.INSTANT_LONG;

    @NotNull
    @Contract("_ -> new")
    static public InstantLong of(Long value) {
        int uuid = hash(TYPE, value);
        if (!instanceMap.containsKey(uuid)) {
            instanceMap.put(uuid, new InstantLong(value));
        }
        return CastUtil.from(instanceMap.get(uuid));
    }

    private InstantLong(Long value) {
        super(ApolloType.INSTANT_INT, value);
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
