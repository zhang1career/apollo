package lab.zhang.apollo.pojo.operator.arithmetic;

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
public class Subtraction extends UnsortableOperator<Integer, Integer> {

    static private final ApolloType TYPE = ApolloType.SUBTRACTION_INT;

    @NotNull
    @Contract(" -> new")
    static public Subtraction of() {
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new Subtraction());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private Subtraction() {
        super(TYPE);
    }

    @Override
    protected Integer doCalc(@NotNull List<? extends Valuable<Integer>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        Integer value = operands.get(0).getValue(paramContext, exeConfig);
        if (value == null) {
            return null;
        }

        Integer subtract = operands.get(1).getValue(paramContext, exeConfig);
        if (subtract == null) {
            return null;
        }

        return value - subtract;
    }
}
