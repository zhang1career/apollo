package unit.lab.zhang.apollo.service;

import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.pojo.operands.instants.InstantBool;
import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operands.variables.VariableBool;
import lab.zhang.apollo.pojo.operands.variables.VariableInt;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operations.UnsortedOperation;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import lab.zhang.apollo.pojo.operators.UnsortableOperator;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import lab.zhang.apollo.pojo.operators.comparators.GreaterThan;
import lab.zhang.apollo.pojo.operators.logics.LogicalEqualTo;
import lab.zhang.apollo.pojo.operators.logics.LogicalOr;
import lab.zhang.apollo.service.LexerService;
import lab.zhang.apollo.service.ParseService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import lab.zhang.apollo.util.CastUtil;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unit.lab.zhang.apollo.repo.operator.ExternalOperatorRepo;

import static org.junit.Assert.*;

public class ParseServiceTest {

    private LexerService lexerService;
    private ParseService parseService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        lexerService = new BasicLexerService();
        parseService = new ParseService(new ExternalOperatorRepo());
    }

    @Test
    public void test_valuableOf_operand() throws Exception {
        String inputCond = "{\"name\":\"在【赠礼】名单中\",\"type\":10,\"value\":\"isInGiftNamelist\"}";
        Token token = lexerService.tokenOf(inputCond);
        VariableBool parsed = CastUtil.from(parseService.valuableOf(token));
        assertNotNull(parsed);

        VariableBool created = VariableBool.of("isInGiftNamelist");
        assertEquals(created, parsed);

        ParamContext paramContext = new ParamContext();
        paramContext.putValue("isInGiftNamelist", false);
        assertFalse(parsed.getValue(paramContext));
    }

    @Test
    public void test_valuableOf_simpleOperation() throws Exception {
        String inputCond = String.format(
                "{\"name\":\"=\",\"type\":%d,\"value\":[{\"name\":\"在【赠礼】名单中\",\"type\":10,\"value\":\"isInGiftNamelist\"},{\"name\":\"真\",\"type\":0,\"value\":true}]}",
                ApolloType.LOGICAL_EQUAL_TO.getId());
        Token token = lexerService.tokenOf(inputCond);
        Operation<Boolean, Boolean> parsed = CastUtil.from(parseService.valuableOf(token));
        assertNotNull(parsed);

        VariableBool op1 = VariableBool.of("isInGiftNamelist");
        InstantBool op2 = InstantBool.of(true);
        SortableOperator<Boolean, Boolean> tor = LogicalEqualTo.of();
        SortedOperation<Boolean, Boolean> created = SortedOperation.of(tor, Lists.list(op1, op2));
        assertEquals(created, parsed);

        ParamContext paramContext = new ParamContext();
        paramContext.putValue("isInGiftNamelist", true);
        assertTrue(parsed.getValue(paramContext));
        paramContext.putValue("isInGiftNamelist", false);
        assertFalse(parsed.getValue(paramContext));
    }

    @Test
    public void test_valuableOf_sortedOperation() throws Exception {
        String inputCond = String.format(
                "{\"name\":\"+\",\"type\":%d,\"value\":[{\"name\":\"2\",\"type\":1,\"value\":2},{\"name\":\"1\",\"type\":1,\"value\":1}]}",
                ApolloType.ADDITION_INT.getId());
        Token token = lexerService.tokenOf(inputCond);
        Operation<Integer, Integer> parsed = CastUtil.from(parseService.valuableOf(token));
        assertNotNull(parsed);

        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> created = SortedOperation.of(tor, Lists.list(op1, op2));
        assertEquals(created, parsed);

        assertEquals(3, parsed.getValue(null).intValue());
    }

    @Test
    public void test_valuableOf_complexOperation() throws Exception {
        String inputCond = String.format(
                "{\"name\":\"or\",\"type\":%d,\"value\":[{\"name\":\"=\",\"type\":%d,\"value\":[{\"name\":\"在【赠礼】名单中\",\"type\":10,\"value\":\"isInGiftNamelist\"},{\"name\":\"真\",\"type\":0,\"value\":true}]},{\"name\":\">\",\"type\":%d,\"value\":[{\"name\":\"年龄\",\"type\":11,\"value\":\"age\"},{\"name\":\"18\",\"type\":1,\"value\":18}]}]}",
                ApolloType.LOGICAL_OR.getId(),
                ApolloType.LOGICAL_EQUAL_TO.getId(),
                ApolloType.GREATER_THAN.getId());
        Token token = lexerService.tokenOf(inputCond);
        Operation<Boolean, Boolean> parsed = CastUtil.from(parseService.valuableOf(token));
        assertNotNull(parsed);

        VariableBool op11 = VariableBool.of("isInGiftNamelist");
        InstantBool op12 = InstantBool.of(true);
        SortableOperator<Boolean, Boolean> tor1 = LogicalEqualTo.of();
        SortedOperation<Boolean, Boolean> tion1 = SortedOperation.of(tor1, Lists.list(op11, op12));
        VariableInt op21 = VariableInt.of("age");
        InstantInt op22 = InstantInt.of(18);
        UnsortableOperator<Boolean, Integer> tor2 = GreaterThan.of();
        UnsortedOperation<Boolean, Integer> tion2 = UnsortedOperation.of(tor2, Lists.list(op21, op22));
        SortableOperator<Boolean, Boolean> tor = LogicalOr.of();
        SortedOperation<Boolean, Boolean> created = SortedOperation.of(tor, Lists.list(tion1, tion2));
        assertEquals(created, parsed);

        ParamContext paramContext = new ParamContext();
        paramContext.putValue("isInGiftNamelist", true);
        paramContext.putValue("age", 20);
        assertTrue(parsed.getValue(paramContext));

        paramContext.putValue("isInGiftNamelist", false);
        paramContext.putValue("age", 20);
        assertTrue(parsed.getValue(paramContext));

        paramContext.putValue("isInGiftNamelist", false);
        paramContext.putValue("age", 17);
        assertFalse(parsed.getValue(paramContext));
    }
}
