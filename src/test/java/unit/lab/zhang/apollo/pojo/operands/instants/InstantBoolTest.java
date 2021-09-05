package unit.lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantBool;
import lab.zhang.apollo.pojo.operands.variables.VariableBool;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        paramContext.putValue("true", true);
        t = VariableBool.of("true");

        paramContext.putValue("false", true);
        f = VariableBool.of("false");

        bool00 = InstantBool.of(true);
        bool01 = InstantBool.of(true);
        bool10 = InstantBool.of(false);
        bool11 = InstantBool.of(false);
    }

    @Test
    public void test_getValue() {
        assertEquals(true, bool00.getValue(paramContext));
        assertEquals(true, bool01.getValue(paramContext));
        assertEquals(false, bool10.getValue(paramContext));
        assertEquals(false, bool11.getValue(paramContext));
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
