package lab.zhang.apollo.pojo.context;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.Operator;
import lab.zhang.apollo.util.HashUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
@Data
public class ExeContext {
    static private final int HASH_SALT = 0x54108879;

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

    @Override
    public int hashCode() {
        return HashUtil.hash(valuable, paramContext, HASH_SALT);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExeContext)) {
            return false;
        }

        ExeContext ec = (ExeContext) obj;
        if(!valuable.equals(ec.valuable)) {
            return false;
        }
        return paramContext.equals(ec.paramContext);
    }
}
