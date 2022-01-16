package lab.zhang.apollo.pojo.operator.arithmetic;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operator.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class Multiplication extends SortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.MULTIPLICATION_INT;

    @NotNull
    @Contract(" -> new")
    static public Multiplication of() {
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new Multiplication());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private Multiplication() {
        super(TYPE);
    }

    //todo: type cast
    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        int ret = 1;
        for (Valuable<Integer> operand : operands) {
            Integer value = operand.getValue(paramContext, exeConfig);
            if (value == null) {
                return null;
            }
            ret *= value;
        }
        return ret;
    }
}
