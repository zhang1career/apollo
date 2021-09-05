package lab.zhang.apollo.service;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.pojo.Operation;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrj
 */
@NoArgsConstructor
abstract public class OptimService {
    public OptimContext optimize(Operation<?, ?> root) {
        return dfs(root);
    }

    private OptimContext dfs(@NotNull Operation<?, ?> root) {
        OptimContext context = new OptimContext();

        if (root.isIndependent()) {
            travel(root, context);
            return this.postTravel(root, context);
        }

        List<OptimContext> contextList = new ArrayList<>();
        for (Valuable<?> child : root.getChildren()) {
            if (!(child instanceof Operation)) {
                continue;
            }
            contextList.add(dfs((Operation<?, ?>) child));
        }
        context = mergeContext(contextList);
        return postTravel(root, context.incrLevel());
    }

    /**
     * Do something on Token travel
     *
     * @param node    Token node
     * @param context analysis context for now
     */
    abstract protected void travel(Operation<?, ?> node, OptimContext context);

    /**
     * Do something after Token travel
     *
     * @param node    Token node
     * @param context analysis context for now
     * @return analysis context for further
     */
    abstract protected OptimContext postTravel(Operation<?, ?> node, OptimContext context);


    /**
     * Merge analysis contexts
     *
     * @param contextList analysis contexts to be merged
     * @return analysis contexts after merged
     */
    abstract protected OptimContext mergeContext(List<OptimContext> contextList);
}
