package lab.zhang.apollo.util;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
public class MapUtil {

    static public <K, V> boolean isNill(Map<K, V> map) {
        return map == null || map.size() <= 0;
    }

    @NotNull
    static public <K, V> Map<K, V> intersect(@NotNull Map<K, V> map, K[] whiteKeys) {
        Map<K, V> ret = new HashMap<>(0);

        if (ArrayUtil.isNill(whiteKeys)) {
            return ret;
        }

        for (K whiteKey : whiteKeys) {
            if (!map.containsKey(whiteKey)) {
                continue;
            }
            ret.put(whiteKey, map.get(whiteKey));
        }

        return ret;
    }
}
