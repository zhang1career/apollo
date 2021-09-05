package lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
public class CachedExeService<R extends Comparable<R>> extends ExeService<R> {

    protected final Map<Integer, Object> resultMap;

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    static public <R extends Comparable<R>> CachedExeService<R> of(OptimContext optimContext) {
        return new CachedExeService<>(optimContext);
    }

    protected CachedExeService(OptimContext optimContext) {
        super(optimContext);
        this.resultMap = new HashMap<>();
    }

    @Override
    public R getValue(ParamContext paramContext) {
        R tempResult = null;
        for (List<Operation<?, ?>> operationList : optimContext.getOperationList()) {
            for (Operation<?, ?> operation : operationList) {
                tempResult = getResult(operation, paramContext);
            }
        }

        result = tempResult;
        return result;
    }

    protected R getResult(Operation<?, ?> operation, ParamContext paramContext) {
        int uuid = hash(operation);
        if (!resultMap.containsKey(uuid)) {
            resultMap.put(uuid, operation.getValue(paramContext));
        }
        return CastUtil.from(resultMap.get(uuid));
    }

    protected int hash(@NotNull Operation<?, ?> operation) {
        return operation.getUuid();
    }
}
