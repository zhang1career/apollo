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

    static private final int HASH_SALT = 0x96A55A69;

    protected Operator<R, V> operator;

    protected List<? extends Valuable<V>> children;

    boolean cacheOn = false;


    protected Operation(Operator<R, V> operator, List<? extends Valuable<V>> children) {
        this.operator = operator;
        this.children = children;
    }

    public void setChildren(List<? extends Valuable<V>> children) {
        this.children = children;
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
    public int hashCode() {
        return HashUtil.hash(operator, children, HASH_SALT);
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
        if (!operator.equals(op.getOperator())) {
            return false;
        }
        if (children.size() != op.getChildren().size()) {
            return false;
        }
        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).equals(op.getChildren().get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isLeaf() {
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
