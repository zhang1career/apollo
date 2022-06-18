package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.exception.TreeBuildException;
import lab.zhang.apollo.pojo.cache.CacheSession;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.enums.RouteDepthEnum;
import lab.zhang.apollo.util.HashUtil;
import lab.zhang.zhangtool.idgen.Idgen;
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

    protected Operator<R, V> operator;

    protected List<? extends Valuable<V>> children;

    private CacheSession<R> cacheSession;

    private Long id;

    static private final int HASH_SALT = 0x96A55A69;


    protected Operation(Operator<R, V> operator, List<? extends Valuable<V>> children, CacheSession<R> cacheSession, Idgen idgen) {
        this.operator = operator;
        this.children = children;
        this.cacheSession = cacheSession;

        for (int retry = 3; retry > 0; retry--) {
            this.id = idgen.genId();
            if (this.id != null) {
                break;
            }
        }
        if (this.id == null) {
            throw new TreeBuildException("Fail to generate operation id.");
        }
    }

    @Override
    public R getValue(ParamContext paramContext, ExeConfig exeConfig) {
        if (cacheSession.isCached(id)) {
            return cacheSession.get(id);
        }

        // if no-depth recurse without cached, return null
        if (exeConfig.getRouteDepth() == RouteDepthEnum.NONE) {
            return null;
        }

        R result = operator.calc(children, paramContext, exeConfig);
        cacheSession.put(id, result);
        return cacheSession.get(id);
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
