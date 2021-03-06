package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantStr;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableStr extends Variable<String> {
    @NotNull
    @Contract("_ -> new")
    static public VariableStr of(String value) {
        return new VariableStr(value);
    }

    private VariableStr(String value) {
        super(ApolloType.VARIABLE_STR, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<String> o) {
        if (!(o instanceof InstantStr) && !(o instanceof VariableStr)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantStr / VariableStr is needed");
        }

        if (o instanceof InstantStr) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }

    @Override
    public String getValue(ParamContext paramContext, ExeConfig exeConfig) {
        String result = super.getValue(paramContext, exeConfig);
        return parseString(result);
    }
}
