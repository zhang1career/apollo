package unit.lab.zhang.apollo.pojo.operand.instant;

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

public class InstantBoolTest {
    private ParamContext paramContext;
    private VariableBool t;
    private VariableBool f;
    private InstantBool bool00;
    private InstantBool bool01;
    private InstantBool bool10;
    private InstantBool bool11;

    private ExeConfig exeConfig;

    @Before
    public void setUp() throws ExecutionException {
        paramContext = new ParamContext();

        paramContext.putValue("true", true);
        t = VariableBool.of("true");

        paramContext.putValue("false", true);
        f = VariableBool.of("false");

        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_getValue() {
        assertEquals(true, bool00.getValue(paramContext, exeConfig));
        assertEquals(true, bool01.getValue(paramContext, exeConfig));
        assertEquals(false, bool10.getValue(paramContext, exeConfig));
        assertEquals(false, bool11.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, bool00.compareTo(t));
        assertNotEquals(0, bool01.compareTo(t));
        assertNotEquals(0, bool10.compareTo(t));
        assertNotEquals(0, bool11.compareTo(t));

        assertNotEquals(0, bool00.compareTo(f));
        assertNotEquals(0, bool01.compareTo(f));
        assertNotEquals(0, bool10.compareTo(f));
        assertNotEquals(0, bool11.compareTo(f));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, bool00.compareTo(bool01));
        assertEquals(0, bool10.compareTo(bool11));
    }

    @Test
    public void test_compareTo_instantGreaterThanVariable() {
        assertEquals(1, bool00.compareTo(t));
        assertEquals(1, bool01.compareTo(t));
        assertEquals(1, bool10.compareTo(f));
        assertEquals(1, bool11.compareTo(f));
    }
}
