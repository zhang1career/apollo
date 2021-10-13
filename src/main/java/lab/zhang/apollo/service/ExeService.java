package lab.zhang.apollo.service;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.ParamContext;

/**
 * @author zhangrj
 */
abstract public class ExeService<R> implements Valuable<R> {

    protected final CompileContext compileContext;

    protected ExeService(CompileContext compileContext) {
        this.compileContext = compileContext;
    }


    @Override
    public R getValue(ParamContext paramContext) {
        return exeValue(paramContext);
    }

    /**
     * Calculate and get value
     * @param paramContext The context that holds indices
     * @return the result of calculation
     */
    abstract public R exeValue(ParamContext paramContext);
}
