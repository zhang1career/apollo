package lab.zhang.apollo.service.exe;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.ExeContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrj
 */
public class CachedExeService<R> extends ExeService<R> {

    static protected final LoadingCache<ExeContext, Object> CACHE =
            CacheBuilder.newBuilder()
                    .concurrencyLevel(8)
                    .initialCapacity(10)
                    .maximumSize(100)
                    .recordStats()
                    .expireAfterWrite(60, TimeUnit.SECONDS)
                    .expireAfterAccess(3600, TimeUnit.SECONDS)
                    .refreshAfterWrite(13, TimeUnit.SECONDS)
                    .removalListener(notification -> {
                        System.out.println(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                    })
                    .build(CacheLoader.from(CachedExeService::getResult));

    static protected Object getResult(@NotNull ExeContext exeContext) {
        Operation<?, ?> operation = exeContext.getOperation();
        ParamContext paramContext = exeContext.getParamContext();
        return operation.getValue(paramContext);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    static public <R> CachedExeService<R> of(CompileContext compileContext) {
        return new CachedExeService<>(compileContext);
    }

    protected CachedExeService(CompileContext compileContext) {
        super(compileContext);
//        this.resultMap = new HashMap<>();
    }

    @Override
    public R exeValue(ParamContext paramContext) {
        Object ret = null;
        for (List<Operation<?, ?>> operationList : compileContext.getOperationList()) {
            for (Operation<?, ?> operation : operationList) {
                ExeContext exeContext = new ExeContext(operation, paramContext);
                ret = CACHE.getUnchecked(exeContext);
            }
        }
        return CastUtil.from(ret);
    }
}
