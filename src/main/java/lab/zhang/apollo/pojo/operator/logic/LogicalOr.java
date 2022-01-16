package lab.zhang.apollo.pojo.operator.logic;

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
public class LogicalOr extends SortableOperator<Boolean, Boolean> {

    static private final ApolloType TYPE = ApolloType.LOGICAL_OR;

    @NotNull
    @Contract(" -> new")
    static public LogicalOr of() {
        if (!Operator.INSTANCE_CACHE.containsKey(TYPE)) {
            Operator.INSTANCE_CACHE.put(TYPE, new LogicalOr());
        }
        return CastUtil.from(Operator.INSTANCE_CACHE.get(TYPE));
    }

    private LogicalOr() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        for (Valuable<Boolean> operand : operands) {
            Boolean value = operand.getValue(paramContext, exeConfig);
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
