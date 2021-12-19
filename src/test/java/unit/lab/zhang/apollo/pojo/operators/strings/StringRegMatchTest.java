package unit.lab.zhang.apollo.pojo.operators.strings;

import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.contexts.EmptyParamContext;
import lab.zhang.apollo.pojo.operands.instants.InstantStr;
import lab.zhang.apollo.pojo.operators.strings.StringRegMatch;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class StringRegMatchTest {
    private StringRegMatch target;
    private ParamContext paramContext;

    @Before
    public void setUp() {
        target = StringRegMatch.of();

        paramContext = EmptyParamContext.of();
    }

    @Test
    public void test_match() {
        InstantStr operandUrl = InstantStr.of("user_info");
        InstantStr operandPattern = InstantStr.of("^user.*");
        operandPattern.getTemp().put("reg_pattern", Pattern.compile(operandPattern.getValue(paramContext)));
        assertTrue(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext));
    }

    @Test
    public void test_match_tempMissed() {
        InstantStr operandUrl = InstantStr.of("user_info");
        InstantStr operandPattern = InstantStr.of("^user.*");
        operandPattern.getTemp().put("reg_pattern_1", Pattern.compile(operandPattern.getValue(paramContext)));
        assertFalse(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext));
    }

//    @Test
//    public void test_match_compileError() {
//        InstantStr operandUrl = InstantStr.of("user_info");
//        InstantStr operandPattern = InstantStr.of("{");
//        operandPattern.getTemp().put("reg_pattern", Pattern.compile(operandPattern.getValue(paramContext)));
//        assertFalse(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext));
//    }
}