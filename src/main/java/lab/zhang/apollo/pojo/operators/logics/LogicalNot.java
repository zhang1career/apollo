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
public class LogicalNot extends SortableOperator<Boolean, Boolean> {

    static private final ApolloType TYPE = ApolloType.LOGICAL_NOT;

    @NotNull
    @Contract(" -> new")
    static public LogicalNot of() {
        if (!Operator.instanceMap.containsKey(TYPE.getId())) {
            Operator.instanceMap.put(TYPE.getId(), new LogicalNot());
        }
        return CastUtil.from(Operator.instanceMap.get(TYPE.getId()));
    }

    private LogicalNot() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext) {
        return !operands.get(0).getValue(paramContext);
    }
}
