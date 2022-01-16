package lab.zhang.apollo.pojo.operand.instant;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.operand.Instant;
import lab.zhang.apollo.pojo.operand.variable.VariableLong;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrj
 */
public class InstantLong extends Instant<Long> {
    static private final LoadingCache<Long, InstantLong> INSTANCE_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, InstantLong>() {
                @Override
                public InstantLong load(@NotNull Long key) {
                    return new InstantLong(key);
                }
            });

    @NotNull
    @Contract("_ -> new")
    static public InstantLong of(Object obj) throws ExecutionException {
        long value = parseLong(obj);
        return INSTANCE_CACHE.get(value);
    }

    private InstantLong(Long value) {
        super(ApolloType.INSTANT_LONG, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Long> o) {
        if (!(o instanceof InstantLong) && !(o instanceof VariableLong)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantInt/VariableInt is needed");
        }
        if ((o instanceof VariableLong)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null, DummyExeConfig.of()));
    }
}
