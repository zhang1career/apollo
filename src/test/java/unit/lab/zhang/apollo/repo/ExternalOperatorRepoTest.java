package unit.lab.zhang.apollo.repo;

import lab.zhang.apollo.entity.ExternalOperatorEntity;
import org.junit.Before;
import org.junit.Test;
import unit.lab.zhang.apollo.repo.operator.ExternalOperatorRepo;

import static org.junit.Assert.*;
public class ExternalOperatorRepoTest {

    private ExternalOperatorRepo target;

    @Before
    public void setUp() {
        target = new ExternalOperatorRepo();
    }

    @Test
    public void test_getItem() {
        ExternalOperatorEntity t = target.getItem(1);
        assertNotNull(t);
        System.out.println(t);
    }

    @Test
    public void test_isExist() {
        boolean t;

        t = target.isExist(0);
        assertFalse(t);
        t = target.isExist(1);
        assertTrue(t);
    }
}
