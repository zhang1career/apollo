package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author zhangrj
 */
public class InstantMap extends Instant<Map<String, Object>> {
    @NotNull
    @Contract("_ -> new")
    static public InstantMap of(Object obj) {
        Map<String, Object> value = parseMap(obj);
        return new InstantMap(value);
    }

    //@todo deep copy
    private InstantMap(Map<String, Object> value) {
        super(ApolloType.INSTANT_MAP, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Map<String, Object>> o) {
        if (!(o instanceof InstantMap) && !(o instanceof VariableMap)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantMap/VariableMap is needed");
        }
        if ((o instanceof VariableMap)) {
            return 1;
        }
        return Integer.compare(hashCode(), o.hashCode());
    }
}
