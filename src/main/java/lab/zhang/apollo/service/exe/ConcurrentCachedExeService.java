package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.ExeContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.ParamContext;
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
    public R exeValue(ParamContext paramContext) {
        ValueSink<Object> valueSink = new ValueSink<>();

        for (List<Operation<?, ?>> operationList : compileContext.getOperationList()) {
            List<Object> resultList = operationList
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
