package lab.zhang.apollo.bo;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;

/**
 * @author zhangrj
 */
public interface Valuable<V> {
    /**
     * Get the value
     * @param paramContext The context that holds indices
     * @param exeConfig The configuration of execution
     * @return value
     */
    V getValue(ParamContext paramContext, ExeConfig exeConfig);
}
