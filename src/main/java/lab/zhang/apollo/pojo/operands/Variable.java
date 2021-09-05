package lab.zhang.apollo.pojo.operands;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.readers.IndexReader;

/**
 * @author zhangrj
 */
abstract public class Variable<V> extends Operand<V, String> {
    public Variable(ApolloType apolloType, String value) {
        super(apolloType, value, IndexReader.of());
    }
}
