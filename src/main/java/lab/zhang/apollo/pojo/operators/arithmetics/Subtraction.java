package lab.zhang.apollo.pojo.operators.arithmetics;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class Subtraction extends UnsortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.SUBTRACTION_INT;

    @NotNull
    @Contract(" -> new")
    static public Subtraction of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new Subtraction());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private Subtraction() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext) {
        return operands.get(0).getValue(paramContext) - operands.get(1).getValue(paramContext);
    }
}
