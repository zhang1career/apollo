package lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableStr;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
public class InstantStr extends Instant<String> {

    static private final ApolloType TYPE = ApolloType.INSTANT_STR;

    static protected Map<String, Operand<?, ?>> instanceMap = new HashMap<>();

    @NotNull
    @Contract("_ -> new")
    static public InstantStr of(String value) {
        if (!instanceMap.containsKey(value)) {
            instanceMap.put(value, new InstantStr(value));
        }
        return CastUtil.from(instanceMap.get(value));
    }

    private InstantStr(String value) {
        super(ApolloType.INSTANT_STR, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<String> o) {
        if (!(o instanceof InstantStr) && !(o instanceof VariableStr)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantStr/VariableStr is needed");
        }
        if ((o instanceof VariableStr)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null));
    }
}
