package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Readable;
import lab.zhang.apollo.util.HashUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
@Data
abstract public class Operand<V, N> implements ComparableValuable<V> {

    static protected <N> int hash(@NotNull ApolloType type, N value) {
        return type.getUuid() ^ HashUtil.codeFrom(value);
    }

    protected ApolloType type;

    protected N value;

    protected Readable<V, N> reader;


    public Operand(ApolloType type, N value, Readable<V, N> reader) {
        this.type = type;
        this.value = value;
        this.reader = reader;
    }


    @Override
    public V getValue(ParamContext paramContext) {
        return reader.read(value, paramContext);
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
        return hashCode() == op.hashCode();
    }

    @Override
    public int hashCode() {
        return hash(type, value);
    }
}
