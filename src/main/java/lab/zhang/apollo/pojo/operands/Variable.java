package lab.zhang.apollo.pojo.operands;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.readers.VariableReader;

/**
 * @author zhangrj
 */
abstract public class Variable<R> extends Operand<R, String> {
    public Variable(ApolloType apolloType, String value) {
        super(apolloType, value, VariableReader.of());
    }
}
