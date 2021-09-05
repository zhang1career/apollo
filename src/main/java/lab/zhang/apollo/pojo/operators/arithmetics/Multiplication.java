package lab.zhang.apollo.pojo.operators.arithmetics;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operators.SortableOperator;
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
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new Multiplication());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private Multiplication() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext) {
        int ret = 1;
        for (Valuable<Integer> operand : operands) {
            ret *= operand.getValue(paramContext);
        }
        return ret;
    }
}
