package unit.lab.zhang.apollo.pojo.reader;

import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
import lab.zhang.apollo.pojo.reader.VariableReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class VariableReaderTest {
    private VariableReader<Integer> target;

    private ParamContext paramContext;
    private VariableInt amount;
    private VariableInt amount1;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = VariableReader.of();

        paramContext = new ParamContext();

        paramContext.putValue("amount", 1234);
        amount = VariableInt.of("amount");
        amount1 = VariableInt.of("amount1");
    }

    @Test
    public void test_notFoundKey() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Cannot find the variable from ParamContext, name: amount2");

        VariableInt amount2 = VariableInt.of("amount2");
        target.read(amount2.getValue(), paramContext);
    }

    @Test
    public void test_context_hit() {
        assertEquals(1234, target.read(amount.getValue(), paramContext).intValue());
    }

    @Test
    public void test_context_miss() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Cannot find the variable");
        target.read(amount1.getValue(), paramContext);
    }
}
