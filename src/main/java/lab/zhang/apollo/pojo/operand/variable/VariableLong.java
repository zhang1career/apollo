package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantLong;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableLong extends Variable<Long> {
    @NotNull
    @Contract("_ -> new")
    static public VariableLong of(String value) {
        return new VariableLong(value);
    }

    private VariableLong(String value) {
        super(ApolloType.VARIABLE_LONG, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Long> o) {
        if (!(o instanceof InstantLong) && !(o instanceof VariableLong)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantLong / VariableLong is needed");
        }

        if (o instanceof InstantLong) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }

    @Override
    public Long getValue(ParamContext paramContext, ExeConfig exeConfig) {
        Long result = super.getValue(paramContext, exeConfig);
        return parseLong(result);
    }
}
