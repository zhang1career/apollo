package lab.zhang.apollo.pojo.operator.arithmetic;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operator.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import lab.zhang.apollo.pojo.Operator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class Division extends SortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.DIVISION_INT;

    @NotNull
    @Contract(" -> new")
    static public Division of() {
        if (!Operator.INSTANCE_CACHE.containsKey(TYPE)) {
            Operator.INSTANCE_CACHE.put(TYPE, new Division());
        }
        return CastUtil.from(Operator.INSTANCE_CACHE.get(TYPE));
    }

    private Division() {
        super(TYPE);
    }

    //todo: type cast
    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        Integer value = operands.get(0).getValue(paramContext, exeConfig);
        if (value == null) {
            return null;
        }
        Integer divider = operands.get(1).getValue(paramContext, exeConfig);
        if (divider == null) {
            return null;
        }
        return value / divider;
    }
}
