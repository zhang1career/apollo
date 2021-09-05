package unit.lab.zhang.apollo.pojo.operations;

import lab.zhang.apollo.pojo.operands.instants.InstantInt;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import lab.zhang.apollo.pojo.operators.SortableOperator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedOperationTest {

    @Before
    public void setUp() {
    }

    @Test
    public void test_sortedOperationCompare_equal() {
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(2);
        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor, Lists.list(op1, op2));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor, Lists.list(op2, op1));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor, Lists.list(op1, op3));
        assertEquals(tion1, tion2);
        assertEquals(tion1, tion3);
    }

    @Test
    public void test_sortedOperationCompare_equal_maxInteger() {
        InstantInt op1 = InstantInt.of(Integer.MIN_VALUE);
        InstantInt op2 = InstantInt.of(Integer.MAX_VALUE);
        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor, Lists.list(op1, op2));
        SortedOperation<Integer, Integer> tion2 = SortedOperation.of(tor, Lists.list(op2, op1));
        assertEquals(tion1, tion2);
    }

    @Test
    public void test_sortedOperationCompare_notEqual() {
        InstantInt op1 = InstantInt.of(1);
        InstantInt op2 = InstantInt.of(2);
        InstantInt op3 = InstantInt.of(3);
        SortableOperator<Integer, Integer> tor = Addition.of();
        SortedOperation<Integer, Integer> tion1 = SortedOperation.of(tor, Lists.list(op1, op2));
        SortedOperation<Integer, Integer> tion3 = SortedOperation.of(tor, Lists.list(op1, op3));
        assertNotEquals(tion1, tion3);
    }
}
