package unit.lab.zhang.apollo.repo.expression;

import unit.lab.zhang.apollo.entity.PlannedExpressionEntity;
import lab.zhang.apollo.pojo.Token;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlannedExpressionRepoTest {
    private PlannedExpressionRepo target;

    @Before
    public void setUp() {
        target = new PlannedExpressionRepo();
    }

//    @Test
//    public void test_create() {
//        Token token = new Token("测试", ApolloType.INSTANT_BOOL, 1);
//        long id = target.create(1, token);
//        assertEquals(4, id);
//    }

    @Test
    public void test_getItem() {
        PlannedExpressionEntity t = target.getItem(1);
        assertNotNull(t);
        System.out.println(t);
    }

    @Test
    public void test_getTokenListIndexById() {
        Map<Long, Token> tokenListIndexById = target.getTokenListIndexById(Lists.list(1L, 2L));

        Token token1 = tokenListIndexById.get(1L);
        assertNotNull(token1);
        System.out.println(token1);

        Token token2 = tokenListIndexById.get(2L);
        assertNotNull(token2);
        System.out.println(token2);

        assertEquals(2, tokenListIndexById.size());
    }
}
