package lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.operand.instant.InstantObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class VariableObject extends Variable<Object> {
    @NotNull
    @Contract("_ -> new")
    static public VariableObject of(String value) {
        return new VariableObject(value);
    }

    private VariableObject(String value) {
        super(ApolloType.VARIABLE_OBJECT, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Object> o) {
        if (!(o instanceof InstantObject) && !(o instanceof VariableObject)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantObject / VariableObject is needed");
        }

        if (o instanceof InstantObject) {
            return -1;
        }

        return Integer.compare(hashCode(), o.hashCode());
    }
}
