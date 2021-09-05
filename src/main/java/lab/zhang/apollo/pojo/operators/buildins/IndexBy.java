package lab.zhang.apollo.pojo.operators.buildins;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
public class IndexBy extends UnsortableOperator<Object, Object> {

    static private final ApolloType TYPE = ApolloType.EXTERNAL_OPERATOR;

    @NotNull
    @Contract(" -> new")
    static public IndexBy of() {
        if (!instanceMap.containsKey(TYPE.getId())) {
            instanceMap.put(TYPE.getId(), new IndexBy());
        }
        return CastUtil.from(instanceMap.get(TYPE.getId()));
    }

    private IndexBy() {
        super(TYPE);
    }

    @Override
    protected Object doCalc(@NotNull List<? extends Valuable<Object>> operands, ParamContext paramContext) {
        List<Map<String, Object>> dataList = CastUtil.from(operands.get(0).getValue(paramContext));
        String keyName = (String) operands.get(1).getValue(paramContext);
        return indexBy(dataList, keyName);
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
