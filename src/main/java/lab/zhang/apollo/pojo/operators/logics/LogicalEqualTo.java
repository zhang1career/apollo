package lab.zhang.apollo.pojo.operators.logics;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class LogicalEqualTo extends SortableOperator<Boolean, Boolean> {

    static private final ApolloType TYPE = ApolloType.LOGICAL_EQUAL_TO;

    @NotNull
    @Contract(" -> new")
    static public LogicalEqualTo of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new LogicalEqualTo());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private LogicalEqualTo() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext) {
        Boolean value0 = operands.get(0).getValue(paramContext);
        Boolean value1 = operands.get(1).getValue(paramContext);
        if (value0 == null || value1 == null) {
            return false;
        }
        return value0.equals(value1);
    }
}
