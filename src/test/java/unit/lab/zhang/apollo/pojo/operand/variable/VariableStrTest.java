package unit.lab.zhang.apollo.pojo.operand.variable;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantStr;
import lab.zhang.apollo.pojo.operand.variable.VariableStr;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    private ExeConfig exeConfig;

    @Rule
    public ExpectedException ee = ExpectedException.none();

    @Before
    public void setUp() {
        paramContext = new ParamContext();

        name0 = VariableStr.of("name");
        name1 = VariableStr.of("name");
        dummy0 = VariableStr.of("dummy");
        dummy1 = VariableStr.of("dummy");
        str0 = InstantStr.of("达拉崩吧");
        str1 = InstantStr.of("");

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_getValue() {
        paramContext.putValue("name", "达拉崩吧");
        paramContext.putValue("dummy", "");

        assertEquals("达拉崩吧", name0.getValue(paramContext, exeConfig));
        assertEquals("", dummy0.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_int() {
        paramContext.putValue("name", 1);
        paramContext.putValue("dummy", 0);

        assertEquals("1", name0.getValue(paramContext, exeConfig));
        assertEquals("0", dummy0.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_getValue_wrong_type_object() {
        ee.expect(RuntimeException.class);
        ee.expectMessage("param type is wrong");

        paramContext.putValue("name", new Object());
        assertEquals("1", name0.getValue(paramContext, exeConfig));
    }

    @Test
    public void test_compareTo_notEqual() {
        paramContext.putValue("name", "达拉崩吧");
        paramContext.putValue("dummy", "");

        assertNotEquals(0, name0.compareTo(dummy0));
    }

    @Test
    public void test_compareTo_equal() {
        paramContext.putValue("name", "达拉崩吧");
        paramContext.putValue("dummy", "");

        assertEquals(0, name0.compareTo(name1));
        assertEquals(0, dummy0.compareTo(dummy1));
    }

    @Test
    public void test_compareTo_variableLessThanInstant() {
        paramContext.putValue("name", "达拉崩吧");
        paramContext.putValue("dummy", "");

        assertEquals(-1, name0.compareTo(str0));
        assertEquals(-1, name0.compareTo(str1));
    }
}
