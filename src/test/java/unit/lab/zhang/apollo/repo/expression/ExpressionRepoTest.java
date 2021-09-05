package unit.lab.zhang.apollo.repo.expression;

import lab.zhang.apollo.entity.ExpressionEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ExpressionRepoTest {
    private ExpressionRepo target;

    @Before
    public void setUp() {
        target = new ExpressionRepo();
    }

    @Test
    public void test_getItem() {
        ExpressionEntity t = target.getItem(1);
        assertNotNull(t);
        System.out.println(t);
    }
}
