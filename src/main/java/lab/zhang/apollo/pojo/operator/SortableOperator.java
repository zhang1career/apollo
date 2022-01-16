package lab.zhang.apollo.pojo.operator;

import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.pojo.ApolloType;

/**
 * @author zhangrj
 */
abstract public class SortableOperator<R, T> extends Operator<R, T> {
    public SortableOperator(ApolloType apolloType) {
        super(apolloType);
    }
}
