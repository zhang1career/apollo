package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableInt extends Variable<Integer> {
    @NotNull
    @Contract("_ -> new")
    static public VariableInt of(String value) {
        return new VariableInt(value);
    }

    private VariableInt(String value) {
        super(ApolloType.VARIABLE_INT, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Integer> o) {
        if (!(o instanceof InstantInt) && !(o instanceof VariableInt)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantInt / VariableInt is needed");
        }

        if (o instanceof InstantInt) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }

    @Override
    public Integer getValue(ParamContext paramContext, ExeConfig exeConfig) {
        Integer result = super.getValue(paramContext, exeConfig);
        return parseInteger(result);
    }
}
