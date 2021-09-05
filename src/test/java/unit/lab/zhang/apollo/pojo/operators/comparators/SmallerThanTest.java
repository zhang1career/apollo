package unit.lab.zhang.apollo.pojo.operators.comparators;

import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.pojo.operators.comparators.SmallerThan;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SmallerThanTest {
    private SmallerThan target;

    VariableInt op0;
    VariableInt op1;
    InstantInt op2;
    InstantInt op3;
    private ParamContext paramContext;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = SmallerThan.of();

        op0 = VariableInt.of("op0");
        op1 = VariableInt.of("op1");
        op2 = InstantInt.of(2);
        op3 = InstantInt.of(3);

        paramContext = new ParamContext();
        paramContext.putValue("op0", 0);
        paramContext.putValue("op1", 1);
    }

    @Test
    public void test_calc() {
        assertTrue(target.calc(Lists.list(op0, op1), paramContext));
        assertFalse(target.calc(Lists.list(op1, op0), paramContext));
        assertTrue(target.calc(Lists.list(op2, op3), paramContext));
        assertFalse(target.calc(Lists.list(op3, op2), paramContext));

        assertTrue(target.calc(Lists.list(op0, op2), paramContext));
        assertFalse(target.calc(Lists.list(op3, op1), paramContext));
    }

    @Test
    public void test_calc_errorCard2() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertTrue(target.calc(Lists.list(op0, op1, op2), paramContext));
    }
}
