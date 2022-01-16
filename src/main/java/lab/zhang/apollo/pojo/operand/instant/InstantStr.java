package lab.zhang.apollo.pojo.operand.instant;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.operand.Instant;
import lab.zhang.apollo.pojo.operand.variable.VariableStr;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantStr extends Instant<String> {
    @NotNull
    @Contract("_ -> new")
    static public InstantStr of(Object obj) {
        String value = parseString(obj);
        return new InstantStr(value);
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
        return this.getValue().compareTo(o.getValue(null, DummyExeConfig.of()));
    }
}
