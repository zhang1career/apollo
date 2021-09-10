package unit.lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.service.exe.ConcurrentCachedExeService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operations.UnsortedOperation;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import lab.zhang.apollo.pojo.operators.arithmetics.Subtraction;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcurrentCachedExeServiceTest {

    @Before
    public void setUp() {
    }

    @Test
    public void test_getValue() {
        InstantInt op0 = InstantInt.of(0);
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(3);
        ParamContext paramContext = new ParamContext();
        IteratingOptimService analyzer = new IteratingOptimService();

        SortableOperator<Integer, Integer> tor1 = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, tion1));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor1, Lists.list(op0, tion2));
        SortedOperation<Integer, Integer> tion4 = SortedOperation.of(tor1, Lists.list(op0, tion3));
        SortedOperation<Integer, Integer> tion5 = SortedOperation.of(tor1, Lists.list(op0, tion4));
        OptimContext context1 = analyzer.optimize(tion5);
        ExeService<Integer> exe1 = ConcurrentCachedExeService.of(context1);
        assertEquals(1, exe1.getValue(paramContext).intValue());

        UnsortableOperator<Integer, Integer> tor2 = Subtraction.of();
        UnsortedOperation<Integer, Integer> tion11 = UnsortedOperation.of(tor2, Lists.list(op0, op1));
        OptimContext context11 = analyzer.optimize(tion11);
        ExeService<Integer> exe11 = ConcurrentCachedExeService.of(context11);
        assertEquals(-1, exe11.getValue(paramContext).intValue());


        UnsortedOperation<Integer, Integer> tion12 = UnsortedOperation.of(tor2, Lists.list(op3, op2));
        OptimContext context12 = analyzer.optimize(tion12);
        ExeService<Integer> exe12 = ConcurrentCachedExeService.of(context12);
        assertEquals(1, exe12.getValue(paramContext).intValue());
    }

    @Test
    public void test_getValue_perf() {
        int count = 1000;
        int depth = 250;

        InstantInt op1 = InstantInt.of(1);
        ParamContext paramContext = new ParamContext();
        IteratingOptimService analyzer = new IteratingOptimService();

        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor, Lists.list(op1, op1));
        SortedOperation<Integer, Integer> tion10 = SortedOperation.of(tor, Lists.list(tion2, tion2, tion2, tion2, tion2));
        SortedOperation<Integer, Integer> tion50 = SortedOperation.of(tor, Lists.list(tion10, tion10, tion10, tion10, tion10));
        SortedOperation<Integer, Integer> tion250 = SortedOperation.of(tor, Lists.list(tion50, tion50, tion50, tion50, tion50));
        OptimContext context250 = analyzer.optimize(tion250);
        ExeService<Integer> exe250 = ConcurrentCachedExeService.of(context250);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            assertEquals(depth, exe250.getValue(paramContext).intValue());
        }
        long end = System.nanoTime();
        long milli = (end - start) / 1000000;
        System.out.println(milli);
    }
}