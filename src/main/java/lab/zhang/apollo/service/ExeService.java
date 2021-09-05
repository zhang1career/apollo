package lab.zhang.apollo.service;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.OptimContext;

/**
 * @author zhangrj
 */
abstract public class ExeService<R extends Comparable<R>> implements Valuable<R> {
    protected R result;

    protected final OptimContext optimContext;

    protected ExeService(OptimContext optimContext) {
        this.optimContext = optimContext;
    }

    
    public R getResult() {
        return result;
    }

    /**
     * Calculate and get value
     * @param paramContext The context that holds indices
     * @return the result of calculation
     */
    @Override
    abstract public R getValue(ParamContext paramContext);
}
