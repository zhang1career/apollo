package lab.zhang.apollo.pojo;

import lab.zhang.apollo.util.CastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
public class ParamContext {

    private final Map<String, Object> indexMap = new HashMap<>();

    public <T> T getValue(String key) {
        return CastUtil.from(indexMap.get(key));
    }

    public void putValue(String key, Object value) {
        indexMap.put(key, value);
    }
}
