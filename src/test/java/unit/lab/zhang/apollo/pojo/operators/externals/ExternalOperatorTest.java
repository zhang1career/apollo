package unit.lab.zhang.apollo.pojo.operators.externals;

import lab.zhang.apollo.controller.ApolloController;
import lab.zhang.apollo.pojo.ParamContext;
import org.junit.Before;
import org.junit.Test;
import unit.lab.zhang.apollo.repo.expression.PlannedExpressionRepo;
import unit.lab.zhang.apollo.repo.operator.ExternalOperatorRepo;

import static org.junit.Assert.assertEquals;

public class ExternalOperatorTest {
    private ApolloController controller;
    private ParamContext paramContext;

    private String operator1;
    private String operator2;

    @Before
    public void setUp() {

        controller = new ApolloController(new ExternalOperatorRepo(), new PlannedExpressionRepo());
        paramContext = new ParamContext();

        operator1 = "{\"name\":\"运算符1\",\"type\":35,\"id\":1,\"value\":null}";
        operator2 = "{\"name\":\"运算符1\",\"type\":35,\"id\":2,\"value\":null}";

    }

    @Test
    public void test_queryAbout() {
        assertEquals("Welcome to Apollo, a calculation center", controller.evalFresh(operator1, paramContext));
    }

    @Test
    public void test_queryMe() {
        assertEquals("zhangrj", controller.evalFresh(operator2, paramContext));
    }

}
