package lab.zhang.apollo.bo;

import lab.zhang.apollo.pojo.ParamContext;

/**
 * @author zhangrj
 */
public interface Valuable<V> {
    /**
     * Get the value
     * @param paramContext The context that holds indices
     * @return value
     */
    V getValue(ParamContext paramContext);
}
