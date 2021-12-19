package lab.zhang.apollo.pojo.operators.logics;

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
public class LogicalOr extends SortableOperator<Boolean, Boolean> {

    static private final ApolloType TYPE = ApolloType.LOGICAL_OR;

    @NotNull
    @Contract(" -> new")
    static public LogicalOr of() {
        if (!Operator.instanceMap.containsKey(TYPE.getId())) {
            Operator.instanceMap.put(TYPE.getId(), new LogicalOr());
        }
        return CastUtil.from(Operator.instanceMap.get(TYPE.getId()));
    }

    private LogicalOr() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext) {
        for (Valuable<Boolean> operand : operands) {
            Boolean value = operand.getValue(paramContext);
            if (value == null) {
                continue;
            }
            if (value) {
                return true;
            }
        }
        return false;
    }
}
