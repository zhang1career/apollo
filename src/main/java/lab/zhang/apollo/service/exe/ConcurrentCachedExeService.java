package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ExeContext;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.util.CastUtil;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zhangrj
 */
public class ConcurrentCachedExeService<R> extends CachedExeService<R> {
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    static public <R> ConcurrentCachedExeService<R> of(CompileContext compileContext) {
        return new ConcurrentCachedExeService<>(compileContext);
    }

    private ConcurrentCachedExeService(CompileContext compileContext) {
        super(compileContext);
    }

    @Override
    protected R doValue(ParamContext paramContext) {
        ValueSink<Object> valueSink = new ValueSink<>();

        for (List<Valuable<?>> valuableList : compileContext.getPrimaryOperationList()) {
            List<Object> resultList = valuableList
                    .parallelStream()
                    .map((operation) -> {
                        ExeContext exeContext = new ExeContext(operation, paramContext);
                        return CACHE.getUnchecked(exeContext);})
                    .collect(Collectors.toList());
            resultList.forEach(valueSink);
        }

        return CastUtil.from(valueSink.getResult());
    }

    @Getter
    private static class ValueSink<R> implements Consumer<R> {
        R result = null;

        @Override
        public void accept(R result) {
            this.result = result;
        }
    }
}
