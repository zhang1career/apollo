package lab.zhang.apollo.pojo.operators.arithmetics;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operators.SortableOperator;
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
        if (!Operator.instanceMap.containsKey(TYPE.getId())) {
            Operator.instanceMap.put(TYPE.getId(), new Division());
        }
        return CastUtil.from(Operator.instanceMap.get(TYPE.getId()));
    }

    private Division() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext) {
        return operands.get(0).getValue(paramContext) / operands.get(1).getValue(paramContext);
    }
}
