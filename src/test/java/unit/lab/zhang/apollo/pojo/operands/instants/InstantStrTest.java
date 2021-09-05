package unit.lab.zhang.apollo.pojo.operands.instants;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantStr;
import lab.zhang.apollo.pojo.operands.variables.VariableStr;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InstantStrTest {
    private ParamContext paramContext;
    private VariableStr name;
    private VariableStr dummy;
    private InstantStr str00;
    private InstantStr str01;
    private InstantStr str10;
    private InstantStr str11;

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        paramContext.putValue("name", "达拉崩吧");
        name = VariableStr.of("name");

        paramContext.putValue("dummy", "");
        dummy = VariableStr.of("dummy");

        str00 = InstantStr.of("达拉崩吧");
        str01 = InstantStr.of("达拉崩吧");
        str10 = InstantStr.of("");
        str11 = InstantStr.of("");
    }

    @Test
    public void test_getValue() {
        assertEquals("达拉崩吧", str00.getValue(paramContext));
        assertEquals("", str10.getValue(paramContext));
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, str00.compareTo(str10));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, str00.compareTo(str01));
        assertEquals(0, str10.compareTo(str11));
    }

    @Test
    public void test_compareTo_instantGreaterThanVariable() {
        assertEquals(1, str00.compareTo(name));
        assertEquals(1, str00.compareTo(dummy));
        assertEquals(1, str10.compareTo(name));
        assertEquals(1, str10.compareTo(dummy));
    }
}
