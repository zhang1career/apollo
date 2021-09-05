package lab.zhang.apollo.pojo.readers;

import lab.zhang.apollo.bo.Readable;
import lab.zhang.apollo.pojo.ParamContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
public class InstantReader<V> implements Readable<V, V> {
    @NotNull
    @Contract(value = " -> new", pure = true)
    static public <V> InstantReader<V> of() {
        return new InstantReader<>();
    }

    private InstantReader() {
    }

    @Override
    public V read(V name, ParamContext paramContext) {
        return name;
    }
}
