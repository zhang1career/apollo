package unit.lab.zhang.apollo.pojo.operands.variables;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantStr;
import lab.zhang.apollo.pojo.operands.variables.VariableStr;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VariableStrTest {
    private ParamContext paramContext;
    private VariableStr name0;
    private VariableStr name1;
    private VariableStr dummy0;
    private VariableStr dummy1;
    private InstantStr str0;
    private InstantStr str1;

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        paramContext.putValue("name", "达拉崩吧");
        name0 = VariableStr.of("name");
        name1 = VariableStr.of("name");

        paramContext.putValue("dummy", "");
        dummy0 = VariableStr.of("dummy");
        dummy1 = VariableStr.of("dummy");

        str0 = InstantStr.of("达拉崩吧");
        str1 = InstantStr.of("");
    }

    @Test
    public void test_getValue() {
        assertEquals("达拉崩吧", name0.getValue(paramContext));
        assertEquals("", dummy0.getValue(paramContext));
    }

    @Test
    public void test_compareTo_notEqual() {
        assertNotEquals(0, name0.compareTo(dummy0));
    }

    @Test
    public void test_compareTo_equal() {
        assertEquals(0, name0.compareTo(name1));
        assertEquals(0, dummy0.compareTo(dummy1));
    }

    @Test
    public void test_compareTo_variableLessThanInstant() {
        assertEquals(-1, name0.compareTo(str0));
        assertEquals(-1, name0.compareTo(str1));
    }
}
