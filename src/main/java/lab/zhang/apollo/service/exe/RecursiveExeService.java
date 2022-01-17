package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.cofig.instance.RecursiveCachedExeConfig;
import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class RecursiveExeService<R> extends ExeService<R> {

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    static public <R> RecursiveExeService<R> of(CompileContext compileContext) {
        return new RecursiveExeService<>(compileContext);
    }

    protected RecursiveExeService(CompileContext compileContext) {
        super(compileContext);
    }

    @Override
    protected R doValue(ParamContext paramContext) {
        return CastUtil.from(compileContext.getOriginalOperation().getValue(paramContext, RecursiveCachedExeConfig.of()));
    }
}
