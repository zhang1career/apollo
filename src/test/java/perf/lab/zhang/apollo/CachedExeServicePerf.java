//package perf.lab.zhang.apollo;
//
//import com.github.noconnor.junitperf.JUnitPerfRule;
//import com.github.noconnor.junitperf.JUnitPerfTest;
//import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
//import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;
//import lab.zhang.apollo.pojo.context.ParamContext;
//import lab.zhang.apollo.service.exe.ParallelExeService;
//import lab.zhang.apollo.service.optim.IteratingOptimService;
//import lab.zhang.apollo.pojo.context.CompileContext;
//import lab.zhang.apollo.service.ExeService;
//import lab.zhang.apollo.pojo.operand.instant.InstantInt;
//import lab.zhang.apollo.pojo.operation.SortedOperation;
//import lab.zhang.apollo.pojo.operator.SortableOperator;
//import lab.zhang.apollo.pojo.operator.arithmetic.Addition;
//import org.assertj.core.util.Lists;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class CachedExeServicePerf {
//    @Rule
//    public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("doc/report/perf/cached-executor.html"));
//
//    private ParamContext paramContext;
//    private ExeService<Integer> target;
//
//    @Before
//    public void setUp() {
//    }
//
//    @Test
//    @JUnitPerfTest(threads = 10, durationMs = 15_000, rampUpPeriodMs = 1_000, warmUpMs = 5_000, maxExecutionsPerSecond = 100_000)
//    @JUnitPerfTestRequirement(percentiles = "90:7, 95:7, 98:7, 99:8", executionsPerSec = 4_000, allowedErrorPercentage = 0.000_000_1f)
//    public void test_getValue() throws Exception {
//        paramContext = new ParamContext();
//        IteratingOptimService analyzer = new IteratingOptimService();
//
//        InstantInt op0 = InstantInt.of(0);
//        InstantInt op1 = InstantInt.of(1);
//
//        SortableOperator<Integer, Integer> tor1 = Addition.of();
//        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor1, Lists.list(op0, op1));
//        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor1, Lists.list(op0, tion1));
//        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor1, Lists.list(op0, tion2));
//        SortedOperation<Integer, Integer> tion4 = SortedOperation.of(tor1, Lists.list(op0, tion3));
//        SortedOperation<Integer, Integer> tion5 = SortedOperation.of(tor1, Lists.list(op0, tion4));
//        CompileContext context = analyzer.optimize(tion5);
//
//        target = ParallelExeService.of(context);
//
//        assertEquals(1, target.getValue(paramContext).intValue());
//    }
//}
