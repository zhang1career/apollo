package syn.lab.zhang.apollo;

import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operation.SortedOperation;
import lab.zhang.apollo.pojo.operator.SortableOperator;
import lab.zhang.apollo.pojo.operator.arithmetic.Addition;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.service.exe.ConcurrentParallelExeService;
import lab.zhang.apollo.service.optim.impl.IteratingOptimServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Before;

public class ConcurrentParallelExeServiceTest {

    private ParamContext paramContext;
    private ExeService<Integer> target1;
    private ExeService<Integer> target2;

    @Before
    public void setUp() throws Exception {
        paramContext = new ParamContext();
        IteratingOptimServiceImpl analyzer = new IteratingOptimServiceImpl();

        InstantInt op0 = InstantInt.of(0);
        InstantInt op1 = InstantInt.of(1);

        SortableOperator<Integer, Integer> tor1 = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, tion1));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor1, Lists.list(op0, tion2));
        SortedOperation<Integer, Integer> tion4 = SortedOperation.of(tor1, Lists.list(op0, tion3));
        SortedOperation<Integer, Integer> tion5 = SortedOperation.of(tor1, Lists.list(op0, tion4));
        CompileContext context = analyzer.optimize(tion5);

        target1 = ConcurrentParallelExeService.of(context);
        target2 = ConcurrentParallelExeService.of(context);
    }

//    @Test
//    public void test_getValue() throws InterruptedException {
//        try (AllInterleavings allInterleavings = new AllInterleavings("Test ConcurrentParallelExeService")) {
//            while (allInterleavings.hasNext()) {
//                Thread first = new Thread(() -> target1.getValue(paramContext));
//                Thread second = new Thread(() -> target2.getValue(paramContext));
//                first.start();
//                second.start();
//                first.join();
//                second.join();
//                assertEquals(target1.getResult(), target2.getResult());
//            }
//        }
//    }
}
