package lab.zhang.apollo.service.optim;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.operands.Variable;
import lab.zhang.apollo.service.OptimService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
public class IteratingOptimService extends OptimService {

    public IteratingOptimService() {
        super();
    }

    @Override
    protected void travel(@NotNull Operation<?, ?> node, CompileContext context) {
        if (node.getChildren() == null) {
            return;
        }
        for (Valuable<?> operand : node.getChildren()) {
            if (!(operand instanceof Variable)) {
                continue;
            }
            context.getRequiredOperandSet().add(CastUtil.from(operand));
        }
    }

    @Override
    protected CompileContext postTravel(Operation<?, ?> node, @NotNull CompileContext context) {
        context.getOperationListOfLevel(context.getLevel()).add(node);
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

            for (int i = 0; i < Math.max(mergedContext.getOperationListSize(), context.getOperationListSize()); i++) {
                if (i >= context.getOperationListSize()) {
                    break;
                }
                mergedContext.getOperationListOfLevel(i).addAll(context.getOperationListOfLevel(i));
            }
        }
        return mergedContext;
    }
}
