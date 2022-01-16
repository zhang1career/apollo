package lab.zhang.apollo.service;

import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ParamContext;

/**
 * @author zhangrj
 */
abstract public class ExeService<R> {

    protected final CompileContext compileContext;

    protected ExeService(CompileContext compileContext) {
        this.compileContext = compileContext;
    }


    public R getValue(ParamContext paramContext) {
        return doValue(paramContext);
    }

    /**
     * Calculate and get value
     * @param paramContext The context that holds indices
     * @return the result of calculation
     */
    abstract protected R doValue(ParamContext paramContext);
}
