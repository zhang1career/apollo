package unit.lab.zhang.apollo.service.lexer;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.service.LexerService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BasicLexerServiceTest {

    private LexerService target;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = new BasicLexerService();
    }

    @Test
    public void test_tokenFrom_with_nullCond() {
        Token token = target.tokenOf(null);
        assertNull(token);
    }

    @Test
    public void test_tokenFrom_with_emptyCond() {
        String inputCond = "";
        Token token = target.tokenOf(inputCond);
        assertNull(token);
    }

    @Test
    public void test_jsonFrom_with_emptyCond() {
        String outputCond = target.jsonOf(null);
        assertNull(outputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_simpleAstCond() {
        String inputCond = "{\"id\":0,\"name\":\"在【赠礼】名单中\",\"type\":10,\"value\":\"isInGiftNamelist\"}";
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);

        assertEquals("在【赠礼】名单中", token.getName());
        assertEquals(10, token.getType().getId());
        assertEquals("isInGiftNamelist", token.getValue());

        String outputCond = target.jsonOf(token);
        assertEquals(inputCond, outputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_combinationAstCond() {
        String inputCond = "{\"id\":0,\"name\":\">\",\"type\":28,\"value\":[{\"id\":0,\"name\":\"年龄\",\"type\":11,\"value\":\"age\"},{\"id\":0,\"name\":\"18\",\"type\":1,\"value\":18}]}";
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);

        assertEquals(">", token.getName());
        assertEquals(28, token.getType().getId());

        String outputCond = target.jsonOf(token);
        assertEquals(inputCond, outputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_complexAstCond() {
        String inputCond = String.format(
                "{\"id\":0,\"name\":\"or\",\"type\":%d,\"value\":[{\"id\":0,\"name\":\"==\",\"type\":%d,\"value\":[{\"id\":0,\"name\":\"在【赠礼】名单中\",\"type\":10,\"value\":\"isInGiftNamelist\"},{\"id\":0,\"name\":\"真\",\"type\":0,\"value\":true}]},{\"id\":0,\"name\":\">\",\"type\":%d,\"value\":[{\"id\":0,\"name\":\"年龄\",\"type\":11,\"value\":\"age\"},{\"id\":0,\"name\":\"18\",\"type\":1,\"value\":18}]}]}",
                ApolloType.LOGICAL_OR.getId(),
                ApolloType.LOGICAL_EQUAL_TO.getId(),
                ApolloType.GREATER_THAN.getId());
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);

        String outputCond = target.jsonOf(token);
        assertEquals(inputCond, outputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_emptyNameCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("name is missing or empty");

        String inputCond = "{\"name\":\"\",\"type\":1,\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_nullNameCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("name is missing or empty");

        String inputCond = "{\"name\":null,\"type\":1,\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_nullTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("set property error, type");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":null,\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_illegalTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("value : -1");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":-1,\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_stringTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("type does not exist");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":\"-1\",\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_emptyStringTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("type does not exist");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":\"\",\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_floatTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("value : 3.14");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":3.14,\"value\":\"isInGiftNamelist\"}";
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_objectTypeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("type does not exist");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":\"Integer.class\",\"value\":\"isInGiftNamelist\"}";
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_with_duplicateTypeCond() {
        String inputCond = "{\"id\":0,\"name\":\"在【赠礼】名单中\",\"type\":1,\"type\":2,\"value\":\"isInGiftNamelist\"}";
        Token token = target.tokenOf(inputCond);
        assertNotNull(token);

        assertEquals("在【赠礼】名单中", token.getName());
        assertEquals(2, token.getType().getId());
        assertEquals("isInGiftNamelist", token.getValue());

        String outputCond = target.jsonOf(token);
        assertEquals("{\"id\":0,\"name\":\"在【赠礼】名单中\",\"type\":2,\"value\":\"isInGiftNamelist\"}", outputCond);
    }

    @Test
    public void test_tokenFrom_and_jsonFrom_without_typeCond() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("type is missing");

        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"value\":\"isInGiftNamelist\"}";
        target.tokenOf(inputCond);
    }


    @Test
    public void test_tokenFrom_with_wrongCard_expectUnaryGivenNone() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = "{\"name\":\"!\",\"type\":34,\"value\":[]}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectUnaryGivenBinary() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = String.format(
                "{\"name\":\"!\",\"type\":%d,\"value\":[{\"name\":\"true\",\"type\":0,\"value\":true},{\"name\":\"false\",\"type\":0,\"value\":false}]}",
                ApolloType.LOGICAL_NOT.getId());
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectUnaryGivenMultinary() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = String.format(
                "{\"name\":\"!\",\"type\":%d,\"value\":[{\"name\":\"true\",\"type\":0,\"value\":true},{\"name\":\"false\",\"type\":0,\"value\":false},{\"name\":\"false\",\"type\":0,\"value\":false}]}",
                ApolloType.LOGICAL_NOT.getId());
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectBinaryGivenNone() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = "{\"name\":\"-\",\"type\":21,\"value\":[]}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectBinaryGivenUnary() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = String.format(
                "{\"name\":\"-\",\"type\":%d,\"value\":[{\"name\":\"1\",\"type\":1,\"value\":1}]}",
                ApolloType.SUBTRACTION_INT.getId());
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectBinaryGivenMultinary() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = String.format(
                "{\"name\":\"-\",\"type\":%d,\"value\":[{\"name\":\"1\",\"type\":1,\"value\":1},{\"name\":\"2\",\"type\":1,\"value\":2},{\"name\":\"3\",\"type\":1,\"value\":3}]}",
                ApolloType.SUBTRACTION_INT.getId());
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongCard_expectMultinaryGivenNone() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("num of operands is wrong");

        String inputCond = "{\"name\":\"+\",\"type\":21,\"value\":[]}";
        target.tokenOf(inputCond);
    }

    @Test
    public void test_tokenFrom_with_wrongType() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("type of operands is wrong");

        String inputCond = String.format(
                "{\"name\":\"+\",\"type\":%d,\"value\":[{\"name\":\"true\",\"type\":0,\"value\":true}]}",
                ApolloType.ADDITION_INT.getId());
        target.tokenOf(inputCond);
    }
}
