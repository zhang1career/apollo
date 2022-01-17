package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.instance.CachedExeConfig;
import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class ParallelExeService<R> extends ExeService<R> {
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    static public <R> ParallelExeService<R> of(CompileContext compileContext) {
        return new ParallelExeService<>(compileContext);
    }

    protected ParallelExeService(CompileContext compileContext) {
        super(compileContext);
    }

    @Override
    protected R doValue(ParamContext paramContext) {
        Object ret = null;
        for (List<Valuable<?>> valuableList : compileContext.getParallelOperationList()) {
            for (Valuable<?> valuable : valuableList) {
                ret = valuable.getValue(paramContext, CachedExeConfig.of());
            }
        }
        return CastUtil.from(ret);
    }
}
