package lab.zhang.apollo.pojo.cofig;

import lombok.Data;

/**
 * @author zhangrj
 */

@Data
public class ExeConfig {
    static public ExeConfig of(boolean recursive, boolean useCache) {
        ExeConfig config = new ExeConfig();
        config.recursive = recursive;
        config.useCache = useCache;
        return config;
    }

    private boolean recursive;
    private boolean useCache;

    private ExeConfig() {
    }
}
