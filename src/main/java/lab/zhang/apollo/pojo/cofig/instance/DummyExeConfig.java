package lab.zhang.apollo.pojo.cofig.instance;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.enums.RouteDepthEnum;

/**
 * @author zhangrj
 */
public class DummyExeConfig {
    static public ExeConfig of() {
        return ExeConfig.of(RouteDepthEnum.NONE);
    }
}
