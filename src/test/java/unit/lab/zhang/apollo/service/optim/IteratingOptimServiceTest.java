package unit.lab.zhang.apollo.service.optim;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.service.exe.CachedExeService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operations.UnsortedOperation;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import lab.zhang.apollo.pojo.operators.arithmetics.Subtraction;
import lab.zhang.apollo.pojo.operators.comparators.GreaterThan;
import lab.zhang.apollo.pojo.operators.logics.LogicalOr;
import org.assertj.core.util.Lists;
import org.junit.*;

import static org.junit.Assert.*;

public class IteratingOptimServiceTest {
    private final IteratingOptimService analyzer = new IteratingOptimService();

    private ParamContext paramContext;

    private final Addition add = Addition.of();
    private final Subtraction sub = Subtraction.of();
    private final GreaterThan gt = GreaterThan.of();
    private final LogicalOr or = LogicalOr.of();

    private VariableInt amount;
    private VariableInt age;

    InstantInt op0 = InstantInt.of(0);
    InstantInt op1 = InstantInt.of(1);
    InstantInt op17 = InstantInt.of(17);
    InstantInt op18 = InstantInt.of(18);
    InstantInt op100 = InstantInt.of(100);

    @Before
    public void setUp() {
        paramContext = new ParamContext();
        paramContext.putValue("amount", 90);
        paramContext.putValue("age", 17);

        amount = VariableInt.of("amount");
        age = VariableInt.of("age");
    }

    @Test
    public void test_analyzer_analyze() {
        SortedOperation<Integer, Integer> addOperation = SortedOperation.of(add, Lists.list(op1, age));
        UnsortedOperation<Boolean, Integer> gtOperation1 = UnsortedOperation.of(gt, Lists.list(addOperation, op18));
        UnsortedOperation<Integer, Integer> subOperation = UnsortedOperation.of(sub, Lists.list(amount, op100));
        UnsortedOperation<Boolean, Integer> gtOperation2 = UnsortedOperation.of(gt, Lists.list(subOperation, op0));
        SortedOperation<Boolean, Boolean> orOperation = SortedOperation.of(or, Lists.list(gtOperation1, gtOperation2));

        OptimContext context = analyzer.optimize(orOperation);

        System.out.println(context.getIndexMap());
        System.out.println(context.getOperationList());

        CachedExeService<Boolean> exe = CachedExeService.of(context);
        assertFalse(exe.getValue(paramContext));
    }
}
