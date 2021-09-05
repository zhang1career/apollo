package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableBool;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantBool extends Instant<Boolean> {

    static private final ApolloType TYPE = ApolloType.INSTANT_BOOL;

    @NotNull
    @Contract("_ -> new")
    static public InstantBool of(Boolean value) {
        int uuid = hash(TYPE, value);
        if (!instanceMap.containsKey(uuid)) {
            instanceMap.put(uuid, new InstantBool(value));
        }
        return CastUtil.from(instanceMap.get(uuid));
    }

    private InstantBool(Boolean value) {
        super(ApolloType.INSTANT_BOOL, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Boolean> o) {
        if (!(o instanceof InstantBool) && !(o instanceof VariableBool)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantBool/VariableBool is needed");
        }
        if ((o instanceof VariableBool)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null));
    }
}
