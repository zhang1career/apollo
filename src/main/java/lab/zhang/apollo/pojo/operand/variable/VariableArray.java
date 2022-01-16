package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantArray;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableArray extends Variable<Object[]> {
    @NotNull
    @Contract("_ -> new")
    static public VariableArray of(String value) {
        return new VariableArray(value);
    }

    private VariableArray(String value) {
        super(ApolloType.VARIABLE_ARRAY, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Object[]> o) {
        if (!(o instanceof InstantArray) && !(o instanceof VariableArray)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantArray / VariableArray is needed");
        }

        if (o instanceof InstantArray) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }

    @Override
    public Object[] getValue(ParamContext paramContext, ExeConfig exeConfig) {
        Object obj = reader.read(value, paramContext);
        if (obj == null) {
            return null;
        }
        return parseArray(obj);
    }
}
