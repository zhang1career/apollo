package unit.lab.zhang.apollo.pojo.operators.arithmetics;

import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.pojo.operators.arithmetics.Division;
import org.assertj.core.util.Lists;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class DivisionTest {
    private Division target;

    VariableInt op0;
    InstantInt op1;
    InstantInt op2;
    InstantInt op4;
    private ParamContext paramContext;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = Division.of();

        op0 = VariableInt.of("zero");
        op1 = InstantInt.of(1);
        op2 = InstantInt.of(2);
        op4 = InstantInt.of(4);

        paramContext = new ParamContext();
        paramContext.putValue("zero", 0);
    }

    @Test
    public void test_calc_integer() {
        assertEquals(2, target.calc(Lists.list(op2, op1), paramContext).intValue());
        assertEquals(4, target.calc(Lists.list(op4, op1), paramContext).intValue());
        assertEquals(2, target.calc(Lists.list(op4, op2), paramContext).intValue());
    }

    @Test
    public void test_calc_castDoubleToInteger() {
        assertEquals(0, target.calc(Lists.list(op0, op1), paramContext).intValue());
        assertEquals(0, target.calc(Lists.list(op0, op2), paramContext).intValue());
        assertEquals(0, target.calc(Lists.list(op1, op2), paramContext).intValue());
    }

    @Test
    public void test_calc_errorCard1() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertEquals(2, target.calc(Lists.list(op0), paramContext).intValue());
    }

    @Test
    public void test_calc_errorCard3() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertEquals(2, target.calc(Lists.list(op0, op1, op2), paramContext).intValue());
    }

    @Test
    public void test_calc_errorDiv0() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("value of some operand is wrong");

        assertEquals(2, target.calc(Lists.list(op2, op0), paramContext).intValue());
    }
}
