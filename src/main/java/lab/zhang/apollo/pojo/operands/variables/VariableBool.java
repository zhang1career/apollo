package lab.zhang.apollo.pojo.operands.variables;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Variable;
import lab.zhang.apollo.pojo.operands.instants.InstantBool;
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
}
