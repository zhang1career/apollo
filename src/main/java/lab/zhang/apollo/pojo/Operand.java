package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Readable;
import lab.zhang.apollo.pojo.cache.CacheSession;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.enums.RouteDepthEnum;
import lab.zhang.apollo.util.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
@Data
abstract public class Operand<V, N> implements ComparableValuable<V> {

    static protected <N> int hash(@NotNull ApolloType type, N value) {
        return type.getId() ^ HashUtil.codeFrom(value);
    }

    static protected Object[] parseArray(Object obj) {
        if (obj == null) {
            return new Object[]{};
        }
        return ArrayUtil.parseArray(obj);
    }

    static protected boolean parseBoolean(Object obj) {
        if (obj == null) {
            return false;
        }
        return BoolUtil.parseBoolean(obj);
    }

    static protected int parseInteger(Object obj) {
        if (obj == null) {
            return 0;
        }
        return NumberUtil.parseInteger(obj);
    }

    static protected long parseLong(Object obj) {
        if (obj == null) {
            return 0;
        }
        return NumberUtil.parseLong(obj);
    }

    static protected Map<String, Object> parseMap(Object obj) {
        if (obj == null) {
            return new HashMap<>(0);
        }
        return MapUtil.parseMap(obj);
    }

    static protected String parseString(Object obj) {
        if (obj == null) {
            return "";
        }
        return StrUtil.parseString(obj);
    }

    protected volatile Map<String, Object> temp = new HashMap<>();

    protected ApolloType type;

    //@todo remmove getter
    protected N value;

    protected Readable<V, N> reader;

    private CacheSession<V> cacheSession;

    private Long id;

    public Operand(ApolloType type, N value, Readable<V, N> reader) {
        this.type = type;
        this.value = value;
        this.reader = reader;
    }


    @Override
    public V getValue(ParamContext paramContext, ExeConfig exeConfig) {
        if (cacheSession.isCached(id)) {
            return cacheSession.get(id);
        }

        // if no-depth recurse without cached, return null
        if (exeConfig.getRouteDepth() == RouteDepthEnum.NONE) {
            return null;
        }

        V result = reader.read(value, paramContext);
        cacheSession.put(id, result);
        return cacheSession.get(id);
    }

    @Override
    public int hashCode() {
        return hash(type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operand)) {
            return false;
        }

        Operand<?, ?> op = (Operand<?, ?>) obj;
        if (type.getId() != op.type.getId()) {
            return false;
        }
        return value == op.value;
    }
}
