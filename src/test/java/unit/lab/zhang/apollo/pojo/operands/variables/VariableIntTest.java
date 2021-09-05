package unit.lab.zhang.apollo.pojo.operands.variables;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VariableIntTest {
    private ParamContext paramContext;
    private VariableInt amount;
    private VariableInt zero0;
    private VariableInt zero1;
    private VariableInt one;
    private VariableInt min;
    private VariableInt max;
    private InstantInt int0;

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        paramContext.putValue("amount", 1234);
        amount = VariableInt.of("amount");

        paramContext.putValue("zero", 0);
        zero0 = VariableInt.of("zero");
        zero1 = VariableInt.of("zero");

        paramContext.putValue("one", 1);
        one = VariableInt.of("one");

        paramContext.putValue("min", Integer.MIN_VALUE);
        min = VariableInt.of("min");

        paramContext.putValue("max", Integer.MAX_VALUE);
        max = VariableInt.of("max");

        int0 = InstantInt.of(0);
    }

    @Test
    public void test_getValue() {
        assertEquals(1234, amount.getValue(paramContext).intValue());
        assertEquals(0, zero0.getValue(paramContext).intValue());
        assertEquals(Integer.MIN_VALUE, min.getValue(paramContext).intValue());
        assertEquals(Integer.MAX_VALUE, max.getValue(paramContext).intValue());
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, zero0.compareTo(one));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, zero0.compareTo(zero1));
    }

    @Test
    public void test_compareTo_variableLessThanInstant() {
        assertEquals(-1, zero0.compareTo(int0));
        assertEquals(-1, one.compareTo(int0));
    }
}
