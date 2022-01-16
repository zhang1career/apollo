package lab.zhang.apollo.service.optim;

import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.service.OptimService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class StagingOptimService extends OptimService {

    public StagingOptimService() {
        super();
    }

    @Override
    protected void travel(@NotNull Operation<?, ?> node, CompileContext context) {
        if (node.getChildren() != null) {
            context.requiredOperandSetAddAll(node.getChildren());
        }
    }

    @Override
    protected CompileContext postTravel(Operation<?, ?> node, @NotNull CompileContext context) {
        context.getPrimaryOperationListOfLevel(context.getLevel()).add(node);
        return context;
    }

    @Override
    protected CompileContext mergeContext(@NotNull List<CompileContext> contextList) {
        CompileContext mergedContext = new CompileContext();
        for (CompileContext context : contextList) {
            if (mergedContext.getLevel() < context.getLevel()) {
                mergedContext.setLevel(context.getLevel());
            }

            mergedContext.getRequiredOperandSet().addAll(context.getRequiredOperandSet());

            for (int i = 0; i < Math.max(mergedContext.getPrimaryOperationListSize(), context.getPrimaryOperationListSize()); i++) {
                if (i >= context.getPrimaryOperationListSize()) {
                    break;
                }
                mergedContext.getPrimaryOperationListOfLevel(i).addAll(context.getPrimaryOperationListOfLevel(i));
            }
        }
        return mergedContext;
    }
}
