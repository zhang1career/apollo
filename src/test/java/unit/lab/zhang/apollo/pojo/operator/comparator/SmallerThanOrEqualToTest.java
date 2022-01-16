package unit.lab.zhang.apollo.pojo.operator.comparator;

import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
import lab.zhang.apollo.pojo.operator.comparator.SmallerThanOrEqualTo;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SmallerThanOrEqualToTest {
    private SmallerThanOrEqualTo target;

    private VariableInt op0;
    private VariableInt op1;
    private InstantInt op2;
    private InstantInt op3;

    private ParamContext paramContext;
    private ExeConfig exeConfig;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        target = SmallerThanOrEqualTo.of();

        op0 = VariableInt.of("op0");
        op1 = VariableInt.of("op1");
        op2 = InstantInt.of(0);
        op3 = InstantInt.of(1);

        paramContext = new ParamContext();
        paramContext.putValue("op0", 0);
        paramContext.putValue("op1", 1);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_calc() {
        assertTrue(target.calc(Lists.list(op0, op0), paramContext, exeConfig));
        assertTrue(target.calc(Lists.list(op0, op1), paramContext, exeConfig));
        assertFalse(target.calc(Lists.list(op1, op2), paramContext, exeConfig));
        assertTrue(target.calc(Lists.list(op1, op3), paramContext, exeConfig));
    }

    @Test
    public void test_calc_errorCard2() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertTrue(target.calc(Lists.list(op0, op1, op2), paramContext, exeConfig));
    }
}
