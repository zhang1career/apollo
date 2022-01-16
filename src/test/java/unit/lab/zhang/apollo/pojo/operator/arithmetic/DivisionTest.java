package unit.lab.zhang.apollo.pojo.operator.arithmetic;

import lab.zhang.apollo.exception.CalculationException;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
import lab.zhang.apollo.pojo.operator.arithmetic.Division;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class DivisionTest {
    private Division target;

    private VariableInt op0;
    private InstantInt op1;
    private InstantInt op2;
    private InstantInt op4;
    private ParamContext paramContext;
    private ExeConfig exeConfig;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        target = Division.of();

        op0 = VariableInt.of("zero");
        op1 = InstantInt.of(1);
        op2 = InstantInt.of(2);
        op4 = InstantInt.of(4);

        paramContext = new ParamContext();
        paramContext.putValue("zero", 0);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_calc_integer() {
        assertEquals(2, target.calc(Lists.list(op2, op1), paramContext, exeConfig).intValue());
        assertEquals(4, target.calc(Lists.list(op4, op1), paramContext, exeConfig).intValue());
        assertEquals(2, target.calc(Lists.list(op4, op2), paramContext, exeConfig).intValue());
    }

    @Test
    public void test_calc_castDoubleToInteger() {
        assertEquals(0, target.calc(Lists.list(op0, op1), paramContext, exeConfig).intValue());
        assertEquals(0, target.calc(Lists.list(op0, op2), paramContext, exeConfig).intValue());
        assertEquals(0, target.calc(Lists.list(op1, op2), paramContext, exeConfig).intValue());
    }

    @Test
    public void test_calc_errorCard1() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertEquals(2, target.calc(Lists.list(op0), paramContext, exeConfig).intValue());
    }

    @Test
    public void test_calc_errorCard3() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("num of operands is wrong");

        assertEquals(2, target.calc(Lists.list(op0, op1, op2), paramContext, exeConfig).intValue());
    }

    @Test
    public void test_calc_errorDiv0() {
        expectedEx.expect(CalculationException.class);
        expectedEx.expectMessage("value of some operand is wrong");

        assertEquals(2, target.calc(Lists.list(op2, op0), paramContext, exeConfig).intValue());
    }
}
