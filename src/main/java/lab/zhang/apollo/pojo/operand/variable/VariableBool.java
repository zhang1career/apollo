package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantBool;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableBool extends Variable<Boolean> {
    @NotNull
    @Contract("_ -> new")
    static public VariableBool of(String value) {
        return new VariableBool(value);
    }

    private VariableBool(String value) {
        super(ApolloType.VARIABLE_BOOL, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Boolean> o) {
        if (!(o instanceof InstantBool) && !(o instanceof VariableBool)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantBool / VariableBool is needed");
        }

        if (o instanceof InstantBool) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }

    @Override
    public Boolean getValue(ParamContext paramContext, ExeConfig exeConfig) {
        Boolean result = super.getValue(paramContext, exeConfig);
        return parseBoolean(result);
    }
}
