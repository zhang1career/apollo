package lab.zhang.apollo.controller;

import lab.zhang.apollo.pojo.context.CompileContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.repo.StorableExpression;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.service.*;
import lab.zhang.apollo.service.exe.CachedExeService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import lab.zhang.apollo.util.CastUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author zhangrj
 */
@Slf4j
public class ApolloController {
    private final LexerService lexerService;
    private final OptimService optimService;
    private final ParseService parseService;
    private final PlanService planService;

    public ApolloController(StorableOperator storableOperator, StorableExpression storableExpression) {
        lexerService = new BasicLexerService();
        parseService = new ParseService(storableOperator);
        optimService = new IteratingOptimService();
        planService = new PlanService(storableExpression);
    }

    public Object eval(String inputCond, ParamContext paramContext) throws ExecutionException {
        Token plannedToken = lexerService.tokenOf(inputCond);
        return compileAndRun(plannedToken, paramContext);
    }

    public Object planAndEval(String inputCond, ParamContext paramContext) throws ExecutionException {
        log.info("hello");
        Token freshToken = lexerService.tokenOf(inputCond);
        Token plannedToken = planService.plan(freshToken);
        return compileAndRun(plannedToken, paramContext);
    }

    private <R> R compileAndRun(Token plannedToken, ParamContext paramContext) throws ExecutionException {
        log.info("world");
        Operation<Object, Object> parsed = CastUtil.from(parseService.valuableOf(plannedToken));
        CompileContext compileContext = optimService.optimize(parsed);
//        ExeService<Integer> exe = ConcurrentCachedExeService.of(compileContext);
        ExeService<Object> exe = CachedExeService.of(compileContext);
        return (R) exe.getValue(paramContext);
    }
}
