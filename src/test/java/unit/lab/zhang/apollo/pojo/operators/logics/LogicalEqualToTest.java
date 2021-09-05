package unit.lab.zhang.apollo.pojo.operators.logics;

import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantBool;
import lab.zhang.apollo.pojo.operands.variables.VariableBool;
import lab.zhang.apollo.pojo.operators.logics.LogicalEqualTo;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogicalEqualToTest {
    private LogicalEqualTo target;

    VariableBool op0;
    VariableBool op1;
    InstantBool op2;
    InstantBool op3;
    private ParamContext paramContext;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = LogicalEqualTo.of();

        op0 = VariableBool.of("t");
        op1 = VariableBool.of("f");
        op2 = InstantBool.of(true);
        op3 = InstantBool.of(false);

        paramContext = new ParamContext();
        paramContext.putValue("t", true);
        paramContext.putValue("f", false);
    }

    @Test
    public void test_calc() {
        assertFalse(target.calc(Lists.list(op0, op1), paramContext));
        assertTrue(target.calc(Lists.list(op0, op2), paramContext));
        assertFalse(target.calc(Lists.list(op1, op2), paramContext));
        assertTrue(target.calc(Lists.list(op1, op3), paramContext));
    }

    @Test
    public void test_calc_errorCard3() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertTrue(target.calc(Lists.list(op0, op1, op2), paramContext));
    }

}
