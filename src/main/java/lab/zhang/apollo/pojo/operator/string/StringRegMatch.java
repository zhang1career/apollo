package lab.zhang.apollo.pojo.operator.string;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.context.instance.EmptyParamContext;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangrj
 */
public class StringRegMatch extends UnsortableOperator<Boolean, String> {
    static public final String TEMP_KEY = "reg_pattern";

    static private final ApolloType TYPE = ApolloType.STRING_REG_MATCH;

    @NotNull
    @Contract(" -> new")
    static public StringRegMatch of() {
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new StringRegMatch());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private StringRegMatch() {
        super(TYPE);
    }

    @Override
    public <T> void postParse(List<? extends Valuable<T>> operands) {
        String str = (String) operands.get(1).getValue(EmptyParamContext.of(), DummyExeConfig.of());
        if (str == null) {
            return;
        }
        Pattern pattern = Pattern.compile(str);
        ((Operand<T, ?>) operands.get(1)).getTemp().put(TEMP_KEY, pattern);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<String>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        String str = operands.get(0).getValue(paramContext, exeConfig);
        Map<String, Object> temp = ((Operand<String, ?>) operands.get(1)).getTemp();
        if (temp == null) {
            return false;
        }

        Pattern pattern = (Pattern) temp.get(TEMP_KEY);
        if (str == null || pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
