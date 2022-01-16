package unit.lab.zhang.apollo.pojo.operation;

import lab.zhang.apollo.pojo.operand.instant.InstantInt;
import lab.zhang.apollo.pojo.operation.UnsortedOperation;
import lab.zhang.apollo.pojo.operator.arithmetic.Subtraction;
import lab.zhang.apollo.pojo.operator.UnsortableOperator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnsortedOperationTest {

    @Before
    public void setUp() {
    }

    @Test
    public void test_unsortedOperationCompare_equal() throws Exception {
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(2);
        UnsortableOperator<Integer, Integer> tor = Subtraction.of();
        UnsortedOperation<Integer, Integer> tion1 = UnsortedOperation.of(tor, Lists.list(op1, op2));
        UnsortedOperation<Integer, Integer> tion2 = UnsortedOperation.of(tor, Lists.list(op2, op1));
        UnsortedOperation<Integer, Integer> tion3 = UnsortedOperation.of(tor, Lists.list(op1, op3));
        assertNotEquals(tion1, tion2);
        assertEquals(tion1, tion3);
    }

    //@todo cache instant value
//    @Test
//    public void test_unsortedOperationCompare_equal_maxInteger() {
//        InstantInt op1 = InstantInt.of(Integer.MIN_VALUE);
//        InstantInt op2 = InstantInt.of(Integer.MAX_VALUE);
//        InstantInt op3 = InstantInt.of(Integer.MAX_VALUE);
//        UnsortableOperator<Integer, Integer> tor = Subtraction.of();
//        UnsortedOperation<Integer, Integer> tion1 = UnsortedOperation.of(tor, Lists.list(op1, op2));
//        UnsortedOperation<Integer, Integer> tion2 = UnsortedOperation.of(tor, Lists.list(op1, op3));
//        assertEquals(tion1, tion2);
//    }

    @Test
    public void test_unsortedOperationCompare_notEqual() throws Exception {
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(3);
        UnsortableOperator<Integer, Integer> tor = Subtraction.of();
        UnsortedOperation<Integer, Integer> tion1 = UnsortedOperation.of(tor, Lists.list(op1, op2));
        UnsortedOperation<Integer, Integer> tion3 = UnsortedOperation.of(tor, Lists.list(op1, op3));
        assertNotEquals(tion1, tion3);
    }
}
