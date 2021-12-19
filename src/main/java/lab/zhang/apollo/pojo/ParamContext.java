package lab.zhang.apollo.pojo;

import lab.zhang.apollo.util.CastUtil;
import lab.zhang.apollo.util.MapUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
@Data
public class ParamContext {

    @NotNull
    static public ParamContext requiredFrom(@Nullable Operator<?, ?> operator, @NotNull ParamContext paramContext) {
        if (operator == null) {
            return new ParamContext(paramContext);
        }
        String[] requiredParams = operator.getRequiredParams();
        if (requiredParams == null) {
            return new ParamContext(paramContext);
        }
        Map<String, Object> intersectMap = MapUtil.intersect(paramContext.getMap(), requiredParams);
        return new ParamContext(intersectMap);
    }

    static public ParamContext of(Map<String, Object> raw) {
        ParamContext paramContext = new ParamContext();
        if (raw == null) {
            return paramContext;
        }
        //@todo deepcopy
        paramContext.map.putAll(raw);
        return paramContext;
    }

    private Map<String, Object> map;

    //@todo private
    public ParamContext() {
        this.map = new HashMap<>();
    }

    //@todo copy
    public ParamContext(Map<String, Object> map) {
        this.map = map;
    }

    //@todo copy
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
