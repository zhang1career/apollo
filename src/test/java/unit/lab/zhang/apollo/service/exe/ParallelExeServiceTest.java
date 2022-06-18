package unit.lab.zhang.apollo.service.exe;

import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operation.SortedOperation;
import lab.zhang.apollo.pojo.operation.UnsortedOperation;
import lab.zhang.apollo.pojo.operator.SortableOperator;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
import lab.zhang.apollo.pojo.operator.arithmetic.Addition;
import lab.zhang.apollo.pojo.operator.arithmetic.Subtraction;
import lab.zhang.apollo.service.ExeService;
import lab.zhang.apollo.service.exe.ParallelExeService;
import lab.zhang.apollo.service.optim.impl.IteratingOptimServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParallelExeServiceTest {

    @Before
    public void setUp() {
    }

    @Test
    public void test_getValue() throws Exception {
        InstantInt op0 = InstantInt.of(0);
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(3);
        ParamContext paramContext = new ParamContext();
        IteratingOptimServiceImpl analyzer = new IteratingOptimServiceImpl();

        SortableOperator<Integer, Integer> tor1 = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, tion1));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor1, Lists.list(op0, tion2));
        SortedOperation<Integer, Integer> tion4 = SortedOperation.of(tor1, Lists.list(op0, tion3));
        SortedOperation<Integer, Integer> tion5 = SortedOperation.of(tor1, Lists.list(op0, tion4));
        CompileContext context1 = analyzer.optimize(tion5);
        ExeService<Integer> exe1 = ParallelExeService.of(context1);
        assertEquals(1, exe1.getValue(paramContext).intValue());

        UnsortableOperator<Integer, Integer> tor2 = Subtraction.of();
        UnsortedOperation<Integer, Integer> tion11 = UnsortedOperation.of(tor2, Lists.list(op0, op1));
        CompileContext context11 = analyzer.optimize(tion11);
        ExeService<Integer> exe11 = ParallelExeService.of(context11);
        assertEquals(-1, exe11.getValue(paramContext).intValue());


        UnsortedOperation<Integer, Integer> tion12 = UnsortedOperation.of(tor2, Lists.list(op3, op2));
        CompileContext context12 = analyzer.optimize(tion12);
        ExeService<Integer> exe12 = ParallelExeService.of(context12);
        assertEquals(1, exe12.getValue(paramContext).intValue());
    }

    @Test
    public void test_getValue_HitCache() throws Exception {
        InstantInt op0 = InstantInt.of(0);
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        ParamContext paramContext = new ParamContext();
        IteratingOptimServiceImpl analyzer = new IteratingOptimServiceImpl();

        SortableOperator<Integer, Integer> tor1 = Addition.of();
        SortedOperation<Integer, Integer> tion0 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, op2));

        CompileContext context0 = analyzer.optimize(tion0);
        CompileContext context1 = analyzer.optimize(tion1);
        CompileContext context2 = analyzer.optimize(tion2);
        ExeService<Integer> exe0 = ParallelExeService.of(context0);
        ExeService<Integer> exe1 = ParallelExeService.of(context1);
        ExeService<Integer> exe2 = ParallelExeService.of(context2);
        assertEquals(1, exe0.getValue(paramContext).intValue());
        assertEquals(1, exe1.getValue(paramContext).intValue());
        assertEquals(2, exe2.getValue(paramContext).intValue());
    }

    @Test
    public void test_getValue_perf() throws Exception {
        int count = 1000;
        int depth = 250;

        InstantInt op1 = InstantInt.of(1);
        ParamContext paramContext = new ParamContext();
        IteratingOptimServiceImpl analyzer = new IteratingOptimServiceImpl();

        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor, Lists.list(op1, op1));
        SortedOperation<Integer, Integer> tion10 = SortedOperation.of(tor, Lists.list(tion2, tion2, tion2, tion2, tion2));
        SortedOperation<Integer, Integer> tion50 = SortedOperation.of(tor, Lists.list(tion10, tion10, tion10, tion10, tion10));
        SortedOperation<Integer, Integer> tion250 = SortedOperation.of(tor, Lists.list(tion50, tion50, tion50, tion50, tion50));
        CompileContext context250 = analyzer.optimize(tion250);
        ExeService<Integer> exe250 = ParallelExeService.of(context250);

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            assertEquals(depth, exe250.getValue(paramContext).intValue());
        }
        long end = System.nanoTime();
        long milli = (end - start) / 1000000;
        System.out.println(milli);
    }
}
