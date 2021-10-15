package unit.lab.zhang.apollo.pojo.readers;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.pojo.readers.VariableReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VariableReaderTest {
    private VariableReader<Integer> target;

    private ParamContext paramContext;
    private VariableInt amount;
    private VariableInt amount1;

    @Before
    public void setUp() {
        target = VariableReader.of();

        paramContext = new ParamContext();

        paramContext.putValue("amount", 1234);
        amount = VariableInt.of("amount");
        amount1 = VariableInt.of("amount1");
    }

    @Test
    public void test_context_hit() {
        assertEquals(1234, target.read(amount.getValue(), paramContext).intValue());
    }

    @Test
    public void test_context_miss() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            target.read(amount1.getValue(), paramContext);
        });
        String expectedMessage = "Cannot find the variable";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
