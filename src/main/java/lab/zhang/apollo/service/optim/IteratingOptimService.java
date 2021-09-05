package lab.zhang.apollo.service.optim;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.pojo.operands.Variable;
import lab.zhang.apollo.service.OptimService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
public class IteratingOptimService extends OptimService {

    public IteratingOptimService() {
        super();
    }

    @Override
    protected void travel(@NotNull Operation<?, ?> node, OptimContext context) {
        if (node.getChildren() == null) {
            return;
        }
        for (Valuable<?> operand : node.getChildren()) {
            if (!(operand instanceof Variable)) {
                continue;
            }
            context.getIndexMap().put(operand.hashCode(), CastUtil.from(operand));
        }
    }

    @Override
    protected OptimContext postTravel(Operation<?, ?> node, @NotNull OptimContext context) {
        context.getOperationListOfLevel(context.getLevel()).add(node);
        return context;
    }

    @Override
    protected OptimContext mergeContext(@NotNull List<OptimContext> contextList) {
        OptimContext mergedContext = new OptimContext();
        for (OptimContext context : contextList) {
            if (mergedContext.getLevel() < context.getLevel()) {
                mergedContext.setLevel(context.getLevel());
            }

            for (Map.Entry<Integer, Operand<?, String>> entry : context.getIndexMap().entrySet()) {
                if (mergedContext.getIndexMap().containsKey(entry.getKey())) {
                    continue;
                }
                mergedContext.getIndexMap().put(entry.getKey(), entry.getValue());
            }

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
