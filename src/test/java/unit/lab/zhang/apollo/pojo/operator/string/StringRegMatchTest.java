package unit.lab.zhang.apollo.pojo.operator.string;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.context.instance.EmptyParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantStr;
import lab.zhang.apollo.pojo.operator.string.StringRegMatch;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class StringRegMatchTest {
    private StringRegMatch target;
    private ParamContext paramContext;
    private ExeConfig exeConfig;

    @Before
    public void setUp() {
        target = StringRegMatch.of();

        paramContext = EmptyParamContext.of();
        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_match() {
        InstantStr operandUrl = InstantStr.of("user_info");
        InstantStr operandPattern = InstantStr.of("^user.*");
        operandPattern.getTemp().put(StringRegMatch.TEMP_KEY, Pattern.compile(operandPattern.getValue(paramContext, exeConfig)));
        assertTrue(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext, exeConfig));
    }

    @Test
    public void test_match_tempMissed() {
        InstantStr operandUrl = InstantStr.of("user_info");
        InstantStr operandPattern = InstantStr.of("^user.*");
        operandPattern.getTemp().put("reg_pattern_1", Pattern.compile(operandPattern.getValue(paramContext, exeConfig)));
        assertFalse(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext, exeConfig));
    }

//    @Test
//    public void test_match_compileError() {
//        InstantStr operandUrl = InstantStr.of("user_info");
//        InstantStr operandPattern = InstantStr.of("{");
//        operandPattern.getTemp().put(StringRegMatch.TEMP_KEY, Pattern.compile(operandPattern.getValue(paramContext)));
//        assertFalse(target.calc(Arrays.asList(operandUrl, operandPattern), paramContext));
//    }
}