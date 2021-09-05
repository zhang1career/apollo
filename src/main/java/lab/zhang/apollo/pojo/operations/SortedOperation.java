package lab.zhang.apollo.pojo.operations;

import lab.zhang.apollo.bo.ComparableValuable;
import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.exception.RunnableCodeException;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.util.CastUtil;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class SortedOperation<R, V> extends Operation<R, V> {
    @NotNull
    @Contract("null, _ -> fail")
    public static <R, V> SortedOperation<R, V> of(Operator<R, V> operator, List<? extends Valuable<V>> operands) {
        if (!(operator instanceof SortableOperator)) {
            throw new IllegalArgumentException("The operator is not sortable");
        }

        List<ComparableValuable<V>> comparableOperands = null;
        if (operands != null && operands.size() > 0) {
            comparableOperands = new ArrayList<>();
            for (Valuable<V> operand : operands) {
                if (!(operand instanceof ComparableValuable)) {
                    throw new IllegalArgumentException("An operand is not comparable");
                }
                comparableOperands.add((ComparableValuable<V>) operand);
            }
            Collections.sort(comparableOperands);
        }
        return new SortedOperation<>(operator, comparableOperands);
    }

    @NotNull
    @Contract("null -> fail")
    public static <R, V> SortedOperation<R, V> of(Operator<R, V> operator) {
        return of(operator, null);
    }

    private SortedOperation(Operator<R, V> operator, List<? extends Valuable<V>> operands) {
        super(operator, operands);
    }

    @Override
    public void setChildren(@NotNull List<? extends Valuable<V>> children) {
        for (Valuable<V> operand : children) {
            if (!(operand instanceof ComparableValuable)) {
                throw new RunnableCodeException("An operand is uncoomparable.");
            }
        }

        Collections.sort(CastUtil.from(children));
        this.children = children;
        this.uuid = hash(this.operator, this.children);
    }
}
