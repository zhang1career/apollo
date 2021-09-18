package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableArray;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantArray extends Instant<Object[]> {

    static private final ApolloType TYPE = ApolloType.INSTANT_ARRAY;

    @NotNull
    @Contract("_ -> new")
    static public InstantArray of(Object[] value) {
        int uuid = hash(TYPE, value);
        if (!instanceMap.containsKey(uuid)) {
            instanceMap.put(uuid, new InstantArray(value));
        }
        return CastUtil.from(instanceMap.get(uuid));
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
