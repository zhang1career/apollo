package lab.zhang.apollo.pojo.operators.arithmetics;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import lab.zhang.apollo.pojo.Operator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class Addition extends SortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.ADDITION_INT;

    @NotNull
    @Contract(" -> new")
    static public Addition of() {
        if (!Operator.instanceMap.containsKey(TYPE.getId())) {
            Operator.instanceMap.put(TYPE.getId(), new Addition());
        }
        return CastUtil.from(Operator.instanceMap.get(TYPE.getId()));
    }

    private Addition() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext) {
        int ret = 0;
        for (Valuable<Integer> operand : operands) {
            ret += operand.getValue(paramContext);
        }
        return ret;
    }
}
