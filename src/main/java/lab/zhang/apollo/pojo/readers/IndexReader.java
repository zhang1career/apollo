package lab.zhang.apollo.pojo.readers;

import lab.zhang.apollo.bo.Readable;
import lab.zhang.apollo.pojo.ParamContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class IndexReader<V> implements Readable<V, String> {
    @NotNull
    @Contract(value = " -> new", pure = true)
    static public <V> IndexReader<V> of() {
        return new IndexReader<>();
    }

    private IndexReader() {
    }

    @Override
    public V read(String name, @NotNull ParamContext paramContext) {
        V ret = paramContext.getValue(name);
        if (ret == null) {
            throw new RuntimeException("Cannot find the index from ParamContext");
        }
        return ret;
    }
}
