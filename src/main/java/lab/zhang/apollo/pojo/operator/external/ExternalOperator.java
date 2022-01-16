package lab.zhang.apollo.pojo.operator.external;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.exception.RunnableCodeException;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author zhangrj
 */
abstract public class ExternalOperator extends UnsortableOperator<Object, Object> {

    static private final ApolloType TYPE = ApolloType.EXTERNAL_OPERATOR;

    static public <T extends ExternalOperator> T of(StorableOperator storableOperator, long id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        ExternalOperator checked = check(storableOperator, id);
        return CastUtil.from(checked);
    }

    static public ExternalOperator check(@NotNull StorableOperator storableOperator, long id) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String clazz = storableOperator.getClazz(id);
        if (clazz == null || clazz.isEmpty()) {
            throw new RunnableCodeException("The external operator has no clazz");
        }

        Constructor<?> constructor = Class.forName(clazz).getDeclaredConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        Object obj = constructor.newInstance();
        if (!(obj instanceof ExternalOperator)) {
            throw new RunnableCodeException("The external operator's clazz is invalid");
        }

        return CastUtil.from(obj);
    }

    protected ExternalOperator() {
        super(TYPE);
    }


    /**
     * Get required names of parameters
     * @return array of parameter names
     */
    @Override
    public String[] getRequiredParams() {
        return null;
    }

    /**
     * Do the calculation
     * @param operands     the operands which are to be calculated
     * @param paramContext some map which contains index name and value
     * @param exeConfig The configuration of execution
     * @return the result of the calculation
     */
    @Override
    abstract protected Object doCalc(List<? extends Valuable<Object>> operands, ParamContext paramContext, ExeConfig exeConfig);
}
