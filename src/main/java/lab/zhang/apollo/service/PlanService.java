package lab.zhang.apollo.service;

import lab.zhang.apollo.exception.TokenizationException;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.repo.StorableExpression;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrj
 */
public class PlanService {

    private final LexerService lexerService;
    private final StorableExpression storableExpression;

    public PlanService(StorableExpression storableExpression) {
        this.lexerService = new BasicLexerService();
        this.storableExpression = storableExpression;
    }

    public Token plan(@NotNull Token root) {
        return dfs(root);
    }

    private Token dfs(@NotNull Token token) {
        if (token.getValue() == null || !(token.getValue() instanceof ArrayList) ) {
            if (token.getType() != ApolloType.PLANNED_OPERATION) {
                return token;
            }
            String expression = this.storableExpression.getExpression(token.getId());
            if (expression == null) {
                throw new TokenizationException("The planned expression is not found. id=" + token.getId());
            }
            return lexerService.tokenOf(expression);
        }

        List<Token> chileTokenList = CastUtil.from(token.getValue());
        if (chileTokenList == null || chileTokenList.size() <= 0) {
            return token;
        }

        for (int i = 0; i < chileTokenList.size(); i++) {
            chileTokenList.set(i, dfs(chileTokenList.get(i)));
        }

        return token;
    }
}
