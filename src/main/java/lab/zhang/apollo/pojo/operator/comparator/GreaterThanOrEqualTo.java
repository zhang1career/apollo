package lab.zhang.apollo.pojo.operator.comparator;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
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
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new GreaterThanOrEqualTo());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private GreaterThanOrEqualTo() {
        super(TYPE);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        Integer value0 = operands.get(0).getValue(paramContext, exeConfig);
        if (value0 == null) {
            return null;
        }

        Integer value1 = operands.get(1).getValue(paramContext, exeConfig);
        if (value1 == null) {
            return null;
        }
        
        return value0 >= value1;
    }
}
