package lab.zhang.apollo.pojo;

import lab.zhang.apollo.util.CastUtil;
import lab.zhang.apollo.util.MapUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
@Data
public class ParamContext {

    @NotNull
    static public ParamContext requiredFrom(@NotNull Operator<?, ?> operator, @NotNull ParamContext paramContext) {
        String[] requiredParams = operator.getRequiredParams();
        if (requiredParams == null) {
            return new ParamContext(paramContext);
        }
        Map<String, Object> intersectMap = MapUtil.intersect(paramContext.getMap(), requiredParams);
        return new ParamContext(intersectMap);
    }


    private Map<String, Object> map;

    public ParamContext() {
        this.map = new HashMap<>();
    }

    public ParamContext(Map<String, Object> map) {
        this.map = map;
    }

    public ParamContext(ParamContext paramContext) {
        this.map = paramContext.getMap();
    }

    public <T> T getValue(String key) {
        return CastUtil.from(map.get(key));
    }

    public void putValue(String key, Object value) {
        map.put(key, value);
    }


}
