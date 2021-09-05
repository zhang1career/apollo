package lab.zhang.apollo.pojo.operators.comparators;

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
public class GreaterThanOrEqualTo extends UnsortableOperator<Boolean, Integer> {

    static private final ApolloType TYPE = ApolloType.GREATER_THAN_OR_EQUAL_TO;

    @NotNull
    @Contract(" -> new")
    static public GreaterThanOrEqualTo of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new GreaterThanOrEqualTo());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private GreaterThanOrEqualTo() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext) {
        return operands.get(0).getValue(paramContext) >= operands.get(1).getValue(paramContext);
    }
}
