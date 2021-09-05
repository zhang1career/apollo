package unit.lab.zhang.apollo.pojo;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.service.LexerService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ApolloTypeTest {
    private LexerService target;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = new BasicLexerService();
    }

    @Test
    public void test_cardNoCheck() {
        String inputCond = String.format("{\"name\":\"NO_CHECK\",\"type\":%d,\"value\":[]}", ApolloType.EXTERNAL_OPERATOR.getId());
        target.tokenOf(inputCond);
    }

    @Test
    public void test_cardUnary() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = String.format("{\"name\":\"UNARY\",\"type\":%d,\"value\":[]}", ApolloType.ADDITION_INT.getId());
        target.tokenOf(inputCond);
    }
}
