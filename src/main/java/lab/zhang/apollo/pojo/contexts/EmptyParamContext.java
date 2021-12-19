package lab.zhang.apollo.pojo.contexts;

import lab.zhang.apollo.pojo.ParamContext;

import java.util.HashMap;

/**
 * @author zhangrj
 */
public class EmptyParamContext {
    static public ParamContext of() {
        return ParamContext.of(new HashMap<>());
    }
}
