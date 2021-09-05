package lab.zhang.apollo.controller;

import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.OptimContext;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.repo.StorableExpression;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.service.*;
import lab.zhang.apollo.service.exe.CachedExeService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import lab.zhang.apollo.service.optim.IteratingOptimService;
import lab.zhang.apollo.util.CastUtil;

/**
 * @author zhangrj
 */
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
        Token freshToken = lexerService.tokenOf(inputCond);
        Token plannedToken = planService.plan(freshToken);
        Operation<Integer, Integer> parsed = CastUtil.from(parserService.valuableOf(plannedToken));
        OptimContext optimContext = optimService.optimize(parsed);
//        ExeService<Integer> exe = ConcurrentCachedExeService.of(optimContext);
        ExeService<Integer> exe = CachedExeService.of(optimContext);
        return exe.getValue(paramContext);
    }
}
