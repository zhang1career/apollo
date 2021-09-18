package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableMap;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author zhangrj
 */
public class InstantMap extends Instant<Map<String, Object>> {

    static private final ApolloType TYPE = ApolloType.INSTANT_MAP;

    @NotNull
    @Contract("_ -> new")
    static public InstantMap of(Map<String, Object> value) {
        int uuid = hash(TYPE, value);
        if (!instanceMap.containsKey(uuid)) {
            instanceMap.put(uuid, new InstantMap(value));
        }
        return CastUtil.from(instanceMap.get(uuid));
    }

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
