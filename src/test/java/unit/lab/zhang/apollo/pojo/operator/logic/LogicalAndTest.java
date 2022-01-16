package unit.lab.zhang.apollo.pojo.operator.logic;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantBool;
import lab.zhang.apollo.pojo.operand.variable.VariableBool;
import lab.zhang.apollo.pojo.operator.logic.LogicalAnd;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogicalAndTest {
    private LogicalAnd target;

    private VariableBool op0;
    private VariableBool op1;
    private InstantBool op2;
    private InstantBool op3;

    private ParamContext paramContext;
    private ExeConfig exeConfig;

    @Before
    public void setUp() throws Exception {
        target = LogicalAnd.of();

        op0 = VariableBool.of("t");
        op1 = VariableBool.of("f");
        op2 = InstantBool.of(true);
        op3 = InstantBool.of(false);

        paramContext = new ParamContext();
        paramContext.putValue("t", true);
        paramContext.putValue("f", false);

        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_calc() {
        assertTrue(target.calc(Lists.list(op0), paramContext, exeConfig));
        assertFalse(target.calc(Lists.list(op1), paramContext, exeConfig));
        assertFalse(target.calc(Lists.list(op0, op1), paramContext, exeConfig));
        assertTrue(target.calc(Lists.list(op0, op2), paramContext, exeConfig));
        assertFalse(target.calc(Lists.list(op1, op3), paramContext, exeConfig));
        assertFalse(target.calc(Lists.list(op0, op1, op2, op3), paramContext, exeConfig));
    }
}
