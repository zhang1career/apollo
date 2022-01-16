package lab.zhang.apollo.pojo.operand;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.reader.VariableReader;

/**
 * @author zhangrj
 */
abstract public class Variable<R> extends Operand<R, String> {
    public Variable(ApolloType apolloType, String value) {
        super(apolloType, value, VariableReader.of());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operand)) {
            return false;
        }

        Operand<?, ?> op = (Operand<?, ?>) obj;
        if (type.getId() != op.getType().getId()) {
            return false;
        }
        return value.equals(op.getValue());
    }
}
