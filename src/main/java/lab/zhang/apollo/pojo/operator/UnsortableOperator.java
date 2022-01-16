package lab.zhang.apollo.pojo.operator;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operator;

/**
 * @author zhangrj
 */
abstract public class UnsortableOperator<R, T> extends Operator<R, T> {
    public UnsortableOperator(ApolloType apolloType) {
        super(apolloType);
    }
}
