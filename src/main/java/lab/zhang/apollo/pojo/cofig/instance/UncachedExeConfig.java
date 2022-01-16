package lab.zhang.apollo.pojo.cofig.instance;

import lab.zhang.apollo.pojo.cofig.ExeConfig;

/**
 * @author zhangrj
 */
public class UncachedExeConfig {
    static public ExeConfig of() {
        return ExeConfig.of(true, false);
    }
}
