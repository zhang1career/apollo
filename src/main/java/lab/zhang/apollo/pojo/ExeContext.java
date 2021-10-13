package lab.zhang.apollo.pojo;

import lab.zhang.apollo.util.HashUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangrj
 */
@Data
public class ExeContext {
    static private final int HASH_SALT = 0x54108879;

    private Operation<?, ?> operation;
    private ParamContext paramContext;


    public ExeContext(@NotNull Operation<?, ?> operation, ParamContext paramContext) {
        this.operation = operation;
        this.paramContext = ParamContext.requiredFrom(operation.getOperator(), paramContext);
    }

    @Override
    public int hashCode() {
        return HashUtil.hash(operation, paramContext, HASH_SALT);
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
        if(!operation.equals(ec.operation)) {
            return false;
        }
        return paramContext.equals(ec.paramContext);
    }
}
