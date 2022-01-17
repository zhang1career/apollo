package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.enums.RecursiveDepthEnum;
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

    private R cache;

    protected Operator<R, V> operator;

    protected List<? extends Valuable<V>> children;


    static private final int HASH_SALT = 0x96A55A69;
    boolean cacheOn = false;


    protected Operation(Operator<R, V> operator, List<? extends Valuable<V>> children) {
        this.operator = operator;
        this.children = children;
        cache = null;
    }

    @Override
    public R getValue(ParamContext paramContext, ExeConfig exeConfig) {
        switch (exeConfig.getRecursiveDepth()) {
            case NONE:
                return exeConfig.isUseCache() ? cache : null;
            case ONCE:
                exeConfig.setRecursiveDepth(RecursiveDepthEnum.NONE);
                return getCachedValue(paramContext, exeConfig);
            case TO_THE_END:
                return getCachedValue(paramContext, exeConfig);
            default:
                return null;
        }
    }

    private R getCachedValue(ParamContext paramContext, ExeConfig exeConfig) {
        if (!exeConfig.isUseCache()) {
            return operator.calc(children, paramContext, exeConfig);
        }

        if (cache == null) {
            cache = operator.calc(children, paramContext, exeConfig);
        }
        return cache;
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

    public void postParse() {
        operator.postParse(children);
    }
}
