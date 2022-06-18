package lab.zhang.apollo.service.optim;

import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.context.CompileContext;


/**
 * @author zhangrj
 */
public interface OptimService {
    CompileContext optimize(Operation<?, ?> root);
}
