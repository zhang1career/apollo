package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.util.HashUtil;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class Operation<R, V> implements ComparableValuable<R> {

    static private final int HASH_MASK = 0x96A55A69;

    static protected <R, V> int hash(Operator<R, V> operator, List<? extends Valuable<V>> operands) {
        return HashUtil.hash(operator, operands, HASH_MASK);
    }


    protected Operator<R, V> operator;

    protected List<? extends Valuable<V>> children;

    protected int uuid;

    boolean cacheOn = false;


    protected Operation(Operator<R, V> operator, List<? extends Valuable<V>> children) {
        this.operator = operator;
        this.children = children;
        this.uuid = hash(this.operator, this.children);
    }

    public void setChildren(List<? extends Valuable<V>> children) {
        this.children = children;
        this.uuid = hash(this.operator, this.children);
    }

    @Override
    public R getValue(ParamContext paramContext) {
        if (operator == null) {
            return null;
        }
        return operator.calc(children, paramContext);
    }

    @Override
    public int compareTo(@NotNull Valuable<R> o) {
        if (!(o instanceof Operation)) {
            return 1;
        }
        int thisHashCode = this.hashCode();
        int thatHashCode = o.hashCode();
        return Integer.compare(thisHashCode, thatHashCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operation)) {
            return false;
        }

        Operation<?, ?> op = (Operation<?, ?>) obj;
        return uuid == op.uuid;
    }

    @Override
    public int hashCode() {
        return hash(operator, children);
    }

    public boolean isIndependent() {
        if (children == null) {
            return true;
        }
        for (Valuable<V> operand : children) {
            if (operand instanceof Operation) {
                return false;
            }
        }
        return true;
    }
}
