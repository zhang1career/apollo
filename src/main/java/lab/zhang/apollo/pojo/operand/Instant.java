package lab.zhang.apollo.pojo.operand;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.reader.InstantReader;

/**
 * @author zhangrj
 */
abstract public class Instant<R> extends Operand<R, R> {

    public Instant(ApolloType apolloType, R value) {
        super(apolloType, value, InstantReader.of());
    }
}
