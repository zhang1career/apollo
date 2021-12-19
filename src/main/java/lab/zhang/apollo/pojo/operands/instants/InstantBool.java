package lab.zhang.apollo.pojo.operands.instants;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.operands.Instant;
import lab.zhang.apollo.pojo.operands.variables.VariableBool;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrj
 */
public class InstantBool extends Instant<Boolean> {
    static private final LoadingCache<Boolean, InstantBool> INSTANCE_CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(new CacheLoader<Boolean, InstantBool>() {
                @Override
                public InstantBool load(@NotNull Boolean key) {
                    return new InstantBool(key);
                }
            });

    @NotNull
    @Contract("_ -> new")
    static public InstantBool of(Object obj) throws ExecutionException {
        boolean value = parseBoolean(obj);
        return INSTANCE_CACHE.get(value);
    }

    private InstantBool(Boolean value) {
        super(ApolloType.INSTANT_BOOL, value);
    }

    @Override
    public int compareTo(@NotNull Valuable<Boolean> o) {
        if (!(o instanceof InstantBool) && !(o instanceof VariableBool)) {
            throw new IllegalArgumentException("An operand is not comparable, an instance of InstantBool/VariableBool is needed");
        }
        if ((o instanceof VariableBool)) {
            return 1;
        }
        return this.getValue().compareTo(o.getValue(null));
    }
}
