package lab.zhang.apollo.bo;

import lab.zhang.apollo.pojo.ParamContext;

import java.util.List;

/**
 * @param <R> The return type
 * @param <V> The parameters type
 * @author zhangrj
 */
public interface Calculable<R, V> {
    /**
     * Calculate
     *
     * @param operands values to be calculated
     * @param paramContext some map which contains index name and value
     * @return result
     */
    R calc(List<? extends Valuable<V>> operands, ParamContext paramContext);
}
