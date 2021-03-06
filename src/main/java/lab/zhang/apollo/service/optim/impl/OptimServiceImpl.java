package lab.zhang.apollo.service.optim.impl;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.service.optim.OptimService;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrj
 */
@NoArgsConstructor
abstract public class OptimServiceImpl implements OptimService {
    @Override
    public CompileContext optimize(Operation<?, ?> root) {
        CompileContext compileContext = dfs(root);
        compileContext.setOriginalOperation(root);
        return compileContext;
    }

    private CompileContext dfs(@NotNull Operation<?, ?> root) {
        CompileContext context = new CompileContext();

        if (root.isLeaf()) {
            travel(root, context);
            return postTravel(root, context);
        }

        List<CompileContext> contextList = new ArrayList<>();
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
    abstract protected void travel(Operation<?, ?> node, CompileContext context);

    /**
     * Do something after Token travel
     *
     * @param node    Token node
     * @param context analysis context for now
     * @return analysis context for further
     */
    abstract protected CompileContext postTravel(Operation<?, ?> node, CompileContext context);


    /**
     * Merge analysis contexts
     *
     * @param contextList analysis contexts to be merged
     * @return analysis contexts after merged
     */
    abstract protected CompileContext mergeContext(List<CompileContext> contextList);
}
