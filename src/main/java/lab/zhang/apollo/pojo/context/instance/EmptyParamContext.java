package lab.zhang.apollo.pojo.context.instance;

import lab.zhang.apollo.pojo.context.ParamContext;

import java.util.HashMap;

/**
 * @author zhangrj
 */
public class EmptyParamContext {
    static public ParamContext of() {
        return ParamContext.of(new HashMap<>());
    }
}
