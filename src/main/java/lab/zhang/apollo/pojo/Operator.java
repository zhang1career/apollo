package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.Calculable;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
abstract public class Operator<R, V> implements Calculable<R, V> {

    static protected Map<ApolloType, Operator<?, ?>> INSTANCE_CACHE = new HashMap<>();


    protected ApolloType type;

    protected Operator(ApolloType type) {
        this.type = type;
    }

    @Override
    public R calc(List<? extends Valuable<V>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        check(operands, paramContext);
        ExeConfig subExeConfig = ExeConfig.dec(exeConfig);
        return doCalc(operands, paramContext, subExeConfig);
    }

    protected void check(List<? extends Valuable<?>> operands, ParamContext paramContext) {
        if (operands == null) {
            if (!type.checkCard(0)) {
                throw new CalculationException("The operands should not be null.");
            }
            return;
        }

        // check cardinality
        if (!type.checkCard(operands.size())) {
            throw new CalculationException("The num of operands is wrong.");
        }

        // check value
        if (!type.checkValue(operands, paramContext)) {
            throw new CalculationException("The value of some operand is wrong.");
        }
    }

    public String[] getRequiredParams() {
        return null;
    }


    public <T> void postParse(List<? extends Valuable<T>> children) {}

    /**
     * Do the calculation
     * @param operands the operands which are to be calculated
     * @param paramContext some map which contains index name and value
     * @param exeConfig The configuration of execution
     * @return the result of calculation
     */
    abstract protected R doCalc(List<? extends Valuable<V>> operands, ParamContext paramContext, ExeConfig exeConfig);

    @Override
    public int hashCode() {
        return type.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operator)) {
            return false;
        }

        Operator<?, ?> op = (Operator<?, ?>) obj;
        return type.getId() == op.type.getId();
    }
}
