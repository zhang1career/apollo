package lab.zhang.apollo.bo;

import lab.zhang.apollo.pojo.context.ParamContext;

/**
 * @author zhangrj
 */
public interface Readable<V, N> {
    /**
     * Read the value of the operand
     * @param name operand name
     * @param paramContext indices context
     * @return the value
     */
    V read(N name, ParamContext paramContext);
}
