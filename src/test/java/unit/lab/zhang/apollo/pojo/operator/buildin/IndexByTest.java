package unit.lab.zhang.apollo.pojo.operator.buildin;

import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.InstantObject;
import lab.zhang.apollo.pojo.operator.buildin.IndexBy;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class IndexByTest {
    private IndexBy target;
    private ParamContext paramContext;
    private ExeConfig exeConfig;

    @Before
    public void setUp() {
        target = IndexBy.of();
        paramContext = new ParamContext();
        exeConfig = DummyExeConfig.of();
    }

    @Test
    public void test_calc() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        HashMap<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("name", "alex");
        user1.put("gender", "male");
        dataList.add(user1);

        HashMap<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("name", "beddy");
        user2.put("gender", "female");
        dataList.add(user2);

        HashMap<String, Object> user3 = new HashMap<>();
        user3.put("id", 2);
        user3.put("name", "charles");
        user3.put("gender", "male");
        dataList.add(user3);

        InstantObject op1 = InstantObject.of(dataList);

        InstantObject op2 = InstantObject.of("id");


        Object objResult = target.calc(Lists.list(op1, op2), paramContext, exeConfig);
        Map<Object, List<Map<String, Object>>> mapResult = (Map<Object, List<Map<String, Object>>>) objResult;
        assertEquals(2, mapResult.size());
        assertEquals(1, mapResult.get(1).size());
        assertEquals(2, mapResult.get(2).size());
    }

}
