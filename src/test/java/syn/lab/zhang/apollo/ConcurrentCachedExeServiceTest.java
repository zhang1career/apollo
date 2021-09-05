package syn.lab.zhang.apollo;

import lab.zhang.apollo.service.exe.ConcurrentCachedExeService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import com.vmlens.api.AllInterleavings;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcurrentCachedExeServiceTest {

    private ParamContext paramContext;
    private ExeService<Integer> target1;
    private ExeService<Integer> target2;

    @Before
    public void setUp() {
        paramContext = new ParamContext();
        IteratingOptimService analyzer = new IteratingOptimService();

        InstantInt op0 = InstantInt.of(0);
        InstantInt op1 = InstantInt.of(1);

        SortableOperator<Integer, Integer> tor1 = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, tion1));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor1, Lists.list(op0, tion2));
        SortedOperation<Integer, Integer> tion4 = SortedOperation.of(tor1, Lists.list(op0, tion3));
        SortedOperation<Integer, Integer> tion5 = SortedOperation.of(tor1, Lists.list(op0, tion4));
        OptimContext context = analyzer.optimize(tion5);

        target1 = ConcurrentCachedExeService.of(context);
        target2 = ConcurrentCachedExeService.of(context);
    }

    @Test
    public void test_getValue() throws InterruptedException {
        try (AllInterleavings allInterleavings = new AllInterleavings("Test ConcurrentCachedExeService")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread(() -> target1.getValue(paramContext));
                Thread second = new Thread(() -> target2.getValue(paramContext));
                first.start();
                second.start();
                first.join();
                second.join();
                assertEquals(target1.getResult(), target2.getResult());
            }
        }
    }
}
