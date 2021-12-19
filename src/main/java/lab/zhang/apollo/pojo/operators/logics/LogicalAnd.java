package lab.zhang.apollo.pojo.operators.logics;

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
public class LogicalAnd extends SortableOperator<Boolean, Boolean> {

    static private final ApolloType TYPE = ApolloType.LOGICAL_AND;

    @NotNull
    @Contract(" -> new")
    static public LogicalAnd of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new LogicalAnd());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private LogicalAnd() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext) {
        for (Valuable<Boolean> operand : operands) {
            Boolean value = operand.getValue(paramContext);
            if (value == null || !value) {
                return false;
            }
        }
        return true;
    }
}
