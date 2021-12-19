package unit.lab.zhang.apollo.controller;

import lab.zhang.apollo.controller.ApolloController;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.ParamContext;
import org.junit.Before;
import org.junit.Test;
import unit.lab.zhang.apollo.repo.expression.PlannedExpressionRepo;
import unit.lab.zhang.apollo.repo.operator.ExternalOperatorRepo;

import static org.junit.Assert.assertEquals;

public class ApolloControllerTest {
    private ApolloController target;

    private ParamContext paramContext;

    @Before
    public void setUp() {
        target = new ApolloController(new ExternalOperatorRepo(), new PlannedExpressionRepo());
        paramContext = new ParamContext();
    }

    @Test
    public void test_eval() throws Exception {
        String inputCond = String.format(
                "{\"id\":0,\"name\":\"+\",\"type\":%d,\"value\":[{\"id\":0,\"name\":\"2\",\"type\":1,\"value\":2},{\"id\":0,\"name\":\"1\",\"type\":1,\"value\":1}]}",
                ApolloType.ADDITION_INT.getId());

        assertEquals(3, target.planAndEval(inputCond, paramContext));
    }
}
