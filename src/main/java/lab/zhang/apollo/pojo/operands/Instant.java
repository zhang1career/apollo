package lab.zhang.apollo.pojo.operands;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.readers.InstantReader;

/**
 * @author zhangrj
 */
abstract public class Instant<R> extends Operand<R, R> {

    public Instant(ApolloType apolloType, R value) {
        super(apolloType, value, InstantReader.of());
    }
}
