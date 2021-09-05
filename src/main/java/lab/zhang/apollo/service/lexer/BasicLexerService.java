package lab.zhang.apollo.service.lexer;

import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.service.LexerService;

/**
 * @author zhangrj
 */
public class BasicLexerService extends LexerService {
    @Override
    protected boolean checkId(Token token) {
        return true;
    }
}
