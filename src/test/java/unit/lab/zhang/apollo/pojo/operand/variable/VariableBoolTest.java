package unit.lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantBool;
import lab.zhang.apollo.pojo.operand.variable.VariableBool;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VariableBoolTest {
    private ParamContext paramContext;
    private VariableBool t0;
    private VariableBool t1;
    private VariableBool f0;
    private VariableBool f1;
    private InstantBool bool00;
    private InstantBool bool01;
    private InstantBool bool10;
    private InstantBool bool11;

    private ExeConfig exeConfig;

    @Before
    public void setUp() throws ExecutionException {
        paramContext = new ParamContext();

        paramContext.putValue("true", true);
        t0 = VariableBool.of("true");
        t1 = VariableBool.of("true");

        paramContext.putValue("false", false);
        f0 = VariableBool.of("false");
        f1 = VariableBool.of("false");

        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_getValue() {
        assertEquals(true, t0.getValue(paramContext, exeConfig));
        assertEquals(false, f0.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, t0.compareTo(bool00));
        assertNotEquals(0, t0.compareTo(bool01));
        assertNotEquals(0, t0.compareTo(bool10));
        assertNotEquals(0, t0.compareTo(bool11));

        assertNotEquals(0, f0.compareTo(bool00));
        assertNotEquals(0, f0.compareTo(bool01));
        assertNotEquals(0, f0.compareTo(bool10));
        assertNotEquals(0, f0.compareTo(bool11));

        assertNotEquals(0, t0.compareTo(f0));
        assertNotEquals(0, t0.compareTo(f1));
        assertNotEquals(0, t1.compareTo(f0));
        assertNotEquals(0, t1.compareTo(f1));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, t0.compareTo(t1));
        assertEquals(0, f0.compareTo(f1));
    }

    @Test
    public void test_compareTo_variableLessThanInstant() {
        assertEquals(-1, t0.compareTo(bool00));
        assertEquals(-1, f0.compareTo(bool01));
    }
}
