package lab.zhang.apollo.pojo.cofig.instance;

import lab.zhang.apollo.pojo.cofig.ExeConfig;

/**
 * @author zhangrj
 */
public class DummyExeConfig {
    static public ExeConfig of() {
        return ExeConfig.of(false, false);
    }
}
