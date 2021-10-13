package lab.zhang.apollo.controller;

import lab.zhang.apollo.pojo.CompileContext;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.repo.StorableExpression;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.service.*;
import lab.zhang.apollo.service.exe.CachedExeService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import lab.zhang.apollo.util.CastUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangrj
 */
@Slf4j
public class ApolloController {
    private final LexerService lexerService;
    private final OptimService optimService;
    private final ParserService parserService;
    private final PlanService planService;

    public ApolloController(StorableOperator storableOperator, StorableExpression storableExpression) {
        lexerService = new BasicLexerService();
        parserService = new ParserService(storableOperator);
        optimService = new IteratingOptimService();
        planService = new PlanService(storableExpression);
    }

    public Object eval(String inputCond, ParamContext paramContext) {
        Token plannedToken = lexerService.tokenOf(inputCond);
        return compileAndRun(plannedToken, paramContext);
    }

    public Object planAndEval(String inputCond, ParamContext paramContext) {
        log.info("hello");
        Token freshToken = lexerService.tokenOf(inputCond);
        Token plannedToken = planService.plan(freshToken);
        return compileAndRun(plannedToken, paramContext);
    }

    private Object compileAndRun(Token plannedToken, ParamContext paramContext) {
        log.info("world");
        Operation<Object, Object> parsed = CastUtil.from(parserService.valuableOf(plannedToken));
        CompileContext compileContext = optimService.optimize(parsed);
//        ExeService<Integer> exe = ConcurrentCachedExeService.of(compileContext);
        ExeService<Object> exe = CachedExeService.of(compileContext);
        return exe.getValue(paramContext);
    }
}
