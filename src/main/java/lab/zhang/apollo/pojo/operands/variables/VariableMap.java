package lab.zhang.apollo.pojo.operands.variables;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Variable;
import lab.zhang.apollo.pojo.operands.instants.InstantMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author zhangrj
 */
public class VariableMap extends Variable<Map<String, Object>> {
    @NotNull
    @Contract("_ -> new")
    static public VariableMap of(String value) {
        return new VariableMap(value);
    }

    private VariableMap(String value) {
        super(ApolloType.VARIABLE_MAP, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Map<String, Object>> o) {
        if (!(o instanceof InstantMap) && !(o instanceof VariableMap)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantMap / VariableMap is needed");
        }

        if (o instanceof InstantMap) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }
}
