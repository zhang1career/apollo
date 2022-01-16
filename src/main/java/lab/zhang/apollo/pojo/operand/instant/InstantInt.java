package lab.zhang.apollo.pojo.operand.instant;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.operand.Instant;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrj
 */
public class InstantInt extends Instant<Integer> {
    static private final LoadingCache<Integer, InstantInt> INSTANCE_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(new CacheLoader<Integer, InstantInt>() {
                @Override
                public InstantInt load(@NotNull Integer key) {
                    return new InstantInt(key);
                }
            });

    @NotNull
    @Contract("_ -> new")
    static public InstantInt of(Object obj) throws ExecutionException {
        int value = parseInteger(obj);
        return INSTANCE_CACHE.get(value);
    }

    private InstantInt(Integer value) {
        super(ApolloType.INSTANT_INT, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Integer> o) {
        if (!(o instanceof InstantInt) && !(o instanceof VariableInt)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantInt/VariableInt is needed");
        }
        if ((o instanceof VariableInt)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null, DummyExeConfig.of()));
    }
}
