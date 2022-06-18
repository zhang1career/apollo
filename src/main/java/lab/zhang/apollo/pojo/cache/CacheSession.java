package lab.zhang.apollo.pojo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
public class CacheSession<V> {

    private final Map<Long, V> map;

    public CacheSession() {
        this.map = new HashMap<>();
    }

    public boolean isCached(Long id) {
        return map.containsKey(id);
    }

    public V put(Long id, V value) {
        return map.put(id, value);
    }

    public V get(Long id) {
        return map.get(id);
    }
}
