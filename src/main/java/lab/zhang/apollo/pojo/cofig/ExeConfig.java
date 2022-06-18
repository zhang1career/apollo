package lab.zhang.apollo.pojo.cofig;

import lab.zhang.apollo.pojo.enums.RouteDepthEnum;
import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class ExeConfig {
    static public ExeConfig of(RouteDepthEnum recursiveDepth) {
        ExeConfig config = new ExeConfig();
        config.routeDepth = recursiveDepth;
        return config;
    }

    static public ExeConfig dec(ExeConfig exeConfig) {
        ExeConfig config = new ExeConfig();
        config.routeDepth = RouteDepthEnum.dec(exeConfig.getRouteDepth());
        return config;
    }

    private RouteDepthEnum routeDepth;

    private ExeConfig() {
    }
}
