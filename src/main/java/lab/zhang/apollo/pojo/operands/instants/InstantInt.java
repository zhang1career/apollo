package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantInt extends Instant<Integer> {

    static private final ApolloType TYPE = ApolloType.INSTANT_INT;

    @NotNull
    @Contract("_ -> new")
    static public InstantInt of(Integer value) {
        int uuid = hash(TYPE, value);
        if (!instanceMap.containsKey(uuid)) {
            instanceMap.put(uuid, new InstantInt(value));
        }
        return CastUtil.from(instanceMap.get(uuid));
    }

    private InstantInt(Integer value) {
        super(ApolloType.INSTANT_INT, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Integer> o) {
        if (!(o instanceof InstantInt) && !(o instanceof VariableInt)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantInt/VariableInt is needed");
        }
        if ((o instanceof VariableInt)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null));
    }
}
