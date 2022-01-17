package lab.zhang.apollo.pojo.cofig.instance;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.enums.RecursiveDepthEnum;

/**
 * @author zhangrj
 */
public class CachedExeConfig {
    static public ExeConfig of() {
        return ExeConfig.of(RecursiveDepthEnum.ONCE, true);
    }
}
