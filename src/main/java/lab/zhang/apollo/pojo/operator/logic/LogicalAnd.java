package lab.zhang.apollo.pojo.operator.logic;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operator.SortableOperator;
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
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new LogicalAnd());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private LogicalAnd() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Boolean>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        for (Valuable<Boolean> operand : operands) {
            Boolean value = operand.getValue(paramContext, exeConfig);
            if (value == null || !value) {
                return false;
            }
        }
        return true;
    }
}
