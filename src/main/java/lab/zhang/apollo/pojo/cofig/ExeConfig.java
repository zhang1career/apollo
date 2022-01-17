package lab.zhang.apollo.pojo.cofig;

import lab.zhang.apollo.pojo.enums.RecursiveDepthEnum;
import lombok.Data;

/**
 * @author zhangrj
 */

@Data
public class ExeConfig {
    static public ExeConfig of(RecursiveDepthEnum recursiveDepth, boolean useCache) {
        ExeConfig config = new ExeConfig();
        config.recursiveDepth = recursiveDepth;
        config.useCache = useCache;
        return config;
    }

    private RecursiveDepthEnum recursiveDepth;
    private boolean useCache;

    private ExeConfig() {
    }
}
