package lab.zhang.apollo.pojo.operations;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class UnsortedOperation<R, V> extends Operation<R, V> {
    @NotNull
    @Contract("null, _ -> fail")
    public static <R, V> UnsortedOperation<R, V> of(Operator<R, V> operator, List<? extends Valuable<V>> operands) {
        if (!(operator instanceof UnsortableOperator)) {
            throw new IllegalArgumentException("The operator is not unsortable");
        }

        return new UnsortedOperation<>(operator, operands);
    }

    @NotNull
    @Contract("null -> fail")
    public static <R, V> UnsortedOperation<R, V> of(Operator<R, V> operator) {
        return of(operator, null);
    }

    private UnsortedOperation(Operator<R, V> operator, List<? extends Valuable<V>> operands) {
        super(operator, operands);
    }
}
