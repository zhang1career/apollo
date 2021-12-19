package lab.zhang.apollo.service;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.ParamContext;

import javax.annotation.Nullable;

/**
 * @author zhangrj
 */
abstract public class ExeService<R> implements Valuable<R> {

    protected final CompileContext compileContext;

    protected ExeService(CompileContext compileContext) {
        this.compileContext = compileContext;
    }


    @Override
    @Nullable
    public R getValue(ParamContext paramContext) {
        return exeValue(paramContext);
    }

    /**
     * Calculate and get value
     * @param paramContext The context that holds indices
     * @return the result of calculation
     */
    @Nullable
    abstract public R exeValue(ParamContext paramContext);
}
