package lab.zhang.apollo.pojo.readers;

import lab.zhang.apollo.bo.Readable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.util.CastUtil;
import lab.zhang.apollo.util.StrUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author zhangrj
 */
public class VariableReader<V> implements Readable<V, String> {
    @NotNull
    @Contract(value = " -> new", pure = true)
    static public <V> VariableReader<V> of() {
        return new VariableReader<>();
    }

    private VariableReader() {
    }

    @Override
    public V read(String name, @NotNull ParamContext paramContext) {
        String[] nameArr = StrUtil.explode(name, "\\.");

        Object value = paramContext.getValue(nameArr[0]);
        if (value == null) {
            throw new RuntimeException("Cannot find the variable from ParamContext, name: " + nameArr[0]);
        }

        for (int i = 1; i < nameArr.length; i++) {
            Map<String, Object> valueMap = CastUtil.from(value);
            if (!valueMap.containsKey(nameArr[i])) {
                throw new RuntimeException("Cannot find the variable from ParamContext, name: " + nameArr[i]);
            }
            value = valueMap.get(nameArr[i]);
        }
        return CastUtil.from(value);
    }
}
