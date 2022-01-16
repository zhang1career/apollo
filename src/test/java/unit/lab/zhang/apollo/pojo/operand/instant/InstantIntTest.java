package unit.lab.zhang.apollo.pojo.operand.instant;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
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

    private ExeConfig exeConfig;

    @Before
    public void setUp() throws Exception {
        paramContext = new ParamContext();

        paramContext.putValue("zero", 0);
        zero = VariableInt.of("zero");

        paramContext.putValue("one", 1);
        one = VariableInt.of("one");

        int00 = InstantInt.of(0);
        int01 = InstantInt.of(0);
        int1 = InstantInt.of(1);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_getValue() {
        assertEquals(0, int00.getValue(paramContext, exeConfig).intValue());
        assertEquals(1, int1.getValue(paramContext, exeConfig).intValue());
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
