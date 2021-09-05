package unit.lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InstantIntTest {
    private ParamContext paramContext;
    private VariableInt zero;
    private VariableInt one;
    private InstantInt int00;
    private InstantInt int01;
    private InstantInt int1;

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        paramContext.putValue("zero", 0);
        zero = VariableInt.of("zero");

        paramContext.putValue("one", 1);
        one = VariableInt.of("one");

        int00 = InstantInt.of(0);
        int01 = InstantInt.of(0);
        int1 = InstantInt.of(1);
    }

    @Test
    public void test_getValue() {
        assertEquals(0, int00.getValue(paramContext).intValue());
        assertEquals(1, int1.getValue(paramContext).intValue());
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, int00.compareTo(int1));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, int00.compareTo(int01));
    }

    @Test
    public void test_compareTo_instantGreaterThanVariable() {
        assertEquals(1, int00.compareTo(zero));
        assertEquals(1, int00.compareTo(one));
    }
}
