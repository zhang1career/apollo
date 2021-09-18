package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.Operation;
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
    static public <R> ConcurrentCachedExeService<R> of(OptimContext optimContext) {
        return new ConcurrentCachedExeService<>(optimContext);
    }

    private ConcurrentCachedExeService(OptimContext optimContext) {
        super(optimContext);
    }

    @Override
    public R getValue(ParamContext paramContext) {
        ValueSink<R> valueSink = new ValueSink<>();

        for (List<Operation<?, ?>> operationList : optimContext.getOperationList()) {
            List<R> resultList = operationList
                    .parallelStream()
                    .map(operation -> getResult(operation, paramContext))
                    .collect(Collectors.toList());
            resultList.forEach(valueSink);
        }

        result = valueSink.getResult();
        return result;
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
