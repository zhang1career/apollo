package unit.lab.zhang.apollo.service;

import lab.zhang.apollo.exception.TokenizationException;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.service.LexerService;
import lab.zhang.apollo.service.PlanService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unit.lab.zhang.apollo.repo.expression.PlannedExpressionRepo;

import static org.junit.Assert.assertEquals;

public class PlanServiceTest {
    private PlanService target;

    private Token p1;
    private Token c1;

    private Token p2;
    private Token c2;

    private Token p3;
    private Token c3;

    private Token p4;
    private Token c4;

    private Token p5;
    private Token c5;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        target = new PlanService(new PlannedExpressionRepo());
        LexerService lexerService = new BasicLexerService();

        String freshExpression1 = "{\"name\":\"指标1\",\"type\":1,\"id\":0,\"value\":1}";
        p1 = lexerService.tokenOf(freshExpression1);
        c1 = lexerService.tokenOf(freshExpression1);

        String freshExpression2 = "{\"name\":\"指标3\",\"type\":36,\"id\":1,\"value\":[{\"name\":\"指标1\",\"type\":1,\"id\":0,\"value\":1},{\"name\":\"指标2\",\"type\":1,\"id\":0,\"value\":2}]}";
        p2 = lexerService.tokenOf(freshExpression2);
        c2 = lexerService.tokenOf(freshExpression2);

        String freshExpression3 = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"name\":\"运算式1\",\"type\":37,\"id\":1,\"value\":null}]}";
        p3 = lexerService.tokenOf(freshExpression3);
        String freshExpression3C = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"name\":\"指标1\",\"type\":1,\"id\":0,\"value\":1}]}";
        c3 = lexerService.tokenOf(freshExpression3C);

        String freshExpression4 = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"name\":\"运算式1\",\"type\":37,\"id\":2,\"value\":null}]}";
        p4 = lexerService.tokenOf(freshExpression4);
        String freshExpression4C = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"id\":2,\"name\":\"指标2\",\"type\":36,\"value\":[{\"id\":0,\"name\":\"指标1\",\"type\":1,\"value\":1}]}]}";
        c4 = lexerService.tokenOf(freshExpression4C);

        String freshExpression5 = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"name\":\"运算式1\",\"type\":37,\"id\":-1,\"value\":null}]}";
        p5 = lexerService.tokenOf(freshExpression5);
        String freshExpression5C = "{\"name\":\"指标2\",\"type\":36,\"id\":2,\"value\":[{\"name\":\"指标1\",\"type\":1,\"id\":0,\"value\":1}]}";
        c5 = lexerService.tokenOf(freshExpression5C);
    }

    @Test
    public void test_planSimpleNormalExpression_shouldBeSame() {
        Token t1 = target.plan(p1);
        assertEquals(c1, t1);
    }

    @Test
    public void test_planComplexNormalExpression_shouldBeSame() {
        Token t2 = target.plan(p2);
        assertEquals(c2, t2);
    }

    @Test
    public void test_planSimpleFreshExpression_shouldReplaceWithPlannedExpression() {
        Token t3 = target.plan(p3);
        assertEquals(c3, t3);
    }

    @Test
    public void test_planComplexFreshExpression_shouldReplaceWithPlannedExpression() {
        Token t4 = target.plan(p4);
        assertEquals(c4, t4);
    }

    @Test
    public void test_planNonExistentFreshExpression_shouldThrowTokenizationException() {
        expectedEx.expect(TokenizationException.class);
        expectedEx.expectMessage("planned expression is not found");
        Token t5 = target.plan(p5);
        assertEquals(c5, t5);
    }
}
