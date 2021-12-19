package lab.zhang.apollo.pojo.operators.strings;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.contexts.EmptyParamContext;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
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

    static private final ApolloType TYPE = ApolloType.STRING_REG_MATCH;

    @NotNull
    @Contract(" -> new")
    static public StringRegMatch of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new StringRegMatch());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private StringRegMatch() {
        super(TYPE);
    }

    @Override
    public <T> void postParse(List<? extends Valuable<T>> operands) {
        String str = (String) operands.get(1).getValue(EmptyParamContext.of());
        if (str == null) {
            return;
        }
        Pattern pattern = Pattern.compile(str);
        ((Operand<T, ?>) operands.get(1)).getTemp().put("reg_pattern", pattern);
    }

    @Override
    protected Boolean doCalc(@NotNull List<? extends Valuable<String>> operands, ParamContext paramContext) {
        String str = operands.get(0).getValue(paramContext);
        Map<String, Object> temp = ((Operand<String, ?>) operands.get(1)).getTemp();
        if (temp == null) {
            return false;
        }

        Pattern pattern = (Pattern) temp.get("reg_pattern");
        if (str == null || pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
