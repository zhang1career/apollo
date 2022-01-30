package unit.lab.zhang.apollo.pojo.operand.instant;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantBool;
import lab.zhang.apollo.pojo.operand.variable.VariableBool;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Rule
    public ExpectedException ee = ExpectedException.none();

    @Before
    public void setUp() throws ExecutionException {
        paramContext = new ParamContext();
        paramContext.putValue("true", true);
        paramContext.putValue("false", false);

        t = VariableBool.of("true");
        f = VariableBool.of("false");

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_getValue() throws ExecutionException {
        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);
        assertEquals(true, bool00.getValue(paramContext, exeConfig));
        assertEquals(true, bool01.getValue(paramContext, exeConfig));
        assertEquals(false, bool10.getValue(paramContext, exeConfig));
        assertEquals(false, bool11.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_string() throws ExecutionException {
        bool00 = InstantBool.of("true");
        bool01 = InstantBool.of("false");
        assertEquals(true, bool00.getValue(paramContext, exeConfig));
        assertEquals(false, bool01.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_int() throws ExecutionException {
        bool00 = InstantBool.of(1);
        bool01 = InstantBool.of(0);
        assertEquals(true, bool00.getValue(paramContext, exeConfig));
        assertEquals(false, bool01.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_int_string() throws ExecutionException {
        bool00 = InstantBool.of("1");
        bool01 = InstantBool.of("0");
        assertEquals(false, bool00.getValue(paramContext, exeConfig));
        assertEquals(false, bool01.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_object() throws ExecutionException {
        ee.expect(RuntimeException.class);
        ee.expectMessage("param type is wrong");

        bool00 = InstantBool.of(new Object());
        assertEquals(true, bool00.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_compareTo_notEqual() throws ExecutionException {
        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);

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
    public void test_compareTo_equal() throws ExecutionException {
        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);

        assertEquals(0, bool00.compareTo(bool01));
        assertEquals(0, bool10.compareTo(bool11));
    }

    @Test
    public void test_compareTo_instantGreaterThanVariable() throws ExecutionException {
        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);

        assertEquals(1, bool00.compareTo(t));
        assertEquals(1, bool01.compareTo(t));
        assertEquals(1, bool10.compareTo(f));
        assertEquals(1, bool11.compareTo(f));
    }
}
