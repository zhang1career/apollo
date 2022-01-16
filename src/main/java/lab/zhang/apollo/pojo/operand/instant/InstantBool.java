package lab.zhang.apollo.pojo.operand.instant;

import com.google.common.collect.ImmutableMap;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.operand.Instant;
import lab.zhang.apollo.pojo.operand.variable.VariableBool;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangrj
 */
public class InstantBool extends Instant<Boolean> {
    static private final Map<Boolean, InstantBool> INSTANCE_CACHE = new HashMap<>(ImmutableMap.of(
            true, new InstantBool(true),
            false, new InstantBool(false)
    ));

    @NotNull
    @Contract("_ -> new")
    static public InstantBool of(Object obj) throws ExecutionException {
        boolean value = parseBoolean(obj);
        return INSTANCE_CACHE.get(value);
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
        return this.getValue().compareTo(o.getValue(null, DummyExeConfig.of()));
    }
}
