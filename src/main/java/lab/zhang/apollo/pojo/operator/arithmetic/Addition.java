package lab.zhang.apollo.pojo.operator.arithmetic;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operator.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
@Slf4j
public class Addition extends SortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.ADDITION_INT;

    @NotNull
    @Contract(" -> new")
    static public Addition of() {
        if (!Operator.INSTANCE_CACHE.containsKey(TYPE)) {
            Operator.INSTANCE_CACHE.put(TYPE, new Addition());
        }
        return CastUtil.from(Operator.INSTANCE_CACHE.get(TYPE));
    }

    private Addition() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        int ret = 0;
        for (Valuable<Integer> operand : operands) {
            Integer value = operand.getValue(paramContext, exeConfig);
            if (value == null) {
                log.info("LogicalAnd gets null param");
                continue;
            }
            ret += value;
        }
        return ret;
    }
}
