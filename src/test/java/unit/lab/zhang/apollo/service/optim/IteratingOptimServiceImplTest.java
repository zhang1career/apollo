package unit.lab.zhang.apollo.service.optim;

import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operation.SortedOperation;
import lab.zhang.apollo.pojo.operation.UnsortedOperation;
import lab.zhang.apollo.service.exe.ParallelExeService;
import lab.zhang.apollo.service.optim.impl.IteratingOptimServiceImpl;
import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operand.variable.VariableInt;
import lab.zhang.apollo.pojo.operator.arithmetic.Addition;
import lab.zhang.apollo.pojo.operator.arithmetic.Subtraction;
import lab.zhang.apollo.pojo.operator.comparator.GreaterThan;
import lab.zhang.apollo.pojo.operator.logic.LogicalOr;
import org.assertj.core.util.Lists;
import org.junit.*;

import static org.junit.Assert.*;

public class IteratingOptimServiceImplTest {
    private final IteratingOptimServiceImpl analyzer = new IteratingOptimServiceImpl();

    private ParamContext paramContext;

    private final Addition add = Addition.of();
    private final Subtraction sub = Subtraction.of();
    private final GreaterThan gt = GreaterThan.of();
    private final LogicalOr or = LogicalOr.of();

    private VariableInt amount;
    private VariableInt age;

    InstantInt op0;
    InstantInt op1;
    InstantInt op17;
    InstantInt op18;
    InstantInt op100;

    @Before
    public void setUp() throws Exception {
        paramContext = new ParamContext();
        paramContext.putValue("amount", 90);
        paramContext.putValue("age", 17);

        amount = VariableInt.of("amount");
        age = VariableInt.of("age");

        op0 = InstantInt.of(0);
        op1 = InstantInt.of(1);
        op17 = InstantInt.of(17);
        op18 = InstantInt.of(18);
        op100 = InstantInt.of(100);
    }

    @Test
    public void test_analyzer_analyze() {
        SortedOperation<Integer, Integer> addOperation = SortedOperation.of(add, Lists.list(op1, age));
        UnsortedOperation<Boolean, Integer> gtOperation1 = UnsortedOperation.of(gt, Lists.list(addOperation, op18));
        UnsortedOperation<Integer, Integer> subOperation = UnsortedOperation.of(sub, Lists.list(amount, op100));
        UnsortedOperation<Boolean, Integer> gtOperation2 = UnsortedOperation.of(gt, Lists.list(subOperation, op0));
        SortedOperation<Boolean, Boolean> orOperation = SortedOperation.of(or, Lists.list(gtOperation1, gtOperation2));

        CompileContext context = analyzer.optimize(orOperation);

        System.out.println(context.getRequiredOperandSet());
        System.out.println(context.getParallelOperationList());

        ParallelExeService<Boolean> exe = ParallelExeService.of(context);
        assertFalse(exe.getValue(paramContext));
    }
}
