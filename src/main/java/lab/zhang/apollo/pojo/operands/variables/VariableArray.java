package lab.zhang.apollo.pojo.operands.variables;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Variable;
import lab.zhang.apollo.pojo.operands.instants.InstantArray;
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
}
