package lab.zhang.apollo.pojo.context;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.Operator;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
@Data
public class ExeContext {
    private Valuable<?> valuable;
    private ParamContext paramContext;


    public ExeContext(@NotNull Valuable<?> valuable, ParamContext paramContext) {
        this.valuable = valuable;

        Operator<?, ?> operator = null;
        if (valuable instanceof Operation) {
            operator = ((Operation<?, ?>) valuable).getOperator();
        }
        this.paramContext = ParamContext.requiredFrom(operator, paramContext);
    }
}
