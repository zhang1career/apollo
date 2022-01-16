package lab.zhang.apollo.pojo.operator.buildin;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//todo: to be replaced by some other method
/**
 * @author zhangrj
 */
public class IndexBy extends UnsortableOperator<Object, Object> {

    static private final ApolloType TYPE = ApolloType.EXTERNAL_OPERATOR;

    @NotNull
    @Contract(" -> new")
    static public IndexBy of() {
        if (!INSTANCE_CACHE.containsKey(TYPE)) {
            INSTANCE_CACHE.put(TYPE, new IndexBy());
        }
        return CastUtil.from(INSTANCE_CACHE.get(TYPE));
    }

    private IndexBy() {
        super(TYPE);
    }

    @Override
    protected Object doCalc(@NotNull List<? extends Valuable<Object>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        Object value = operands.get(0).getValue(paramContext, exeConfig);
        if (value == null) {
            return null;
        }

        Object key = operands.get(1).getValue(paramContext, exeConfig);
        if (key == null) {
            return null;
        }

        List<Map<String, Object>> dataList = CastUtil.from(value);
        return indexBy(dataList, (String) key);
    }

    @NotNull
    private Map<Object, List<Map<String, Object>>> indexBy(@NotNull List<Map<String, Object>> dataList, String keyName) {
        Map<Object, List<Map<String, Object>>> ret = new HashMap<>(0);

        for (Map<String, Object> data : dataList) {
            Object keyValue = data.get(keyName);
            List<Map<String, Object>> subRet = ret.get(keyValue);
            if (subRet == null) {
                subRet = new ArrayList<>();
            }
            subRet.add(data);
            ret.put(keyValue, subRet);
        }
        return ret;
    }
}
