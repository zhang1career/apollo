package unit.lab.zhang.apollo.repo;

import lab.zhang.apollo.entity.Identical;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepo {

    static private SqlSession sqlSession = null;

    @NotNull
    static public <P, R> List<R> columnOf(@NotNull List<P> inputList, Convertible<P, R> convertor) {
        List<R> output = new ArrayList<>(inputList.size());
        for (P input : inputList) {
            output.add(convertor.covertFrom(input));
        }

        return output;
    }

    @NotNull
    static public <P, R> Map<Long, R> columnOf(@NotNull Map<Long, P> inputMap, Convertible<P, R> convertor) {
        Map<Long, R> output = new HashMap<>(inputMap.size());
        for (Map.Entry<Long, P> entry : inputMap.entrySet()) {
            output.put(entry.getKey(), convertor.covertFrom(entry.getValue()));
        }

        return output;
    }

    @NotNull
    static public <T extends Identical> Map<Long, T> indexById(@NotNull List<T> list) {
        Map<Long, T> map = new HashMap<>(list.size());
        for (T item : list) {
            map.put(item.getId(), item);
        }
        return map;
    }

    public <T> T getMapper(Class<T> clazz) throws IOException {
        return getSqlSession().getMapper(clazz);
    }

    private SqlSession getSqlSession() throws IOException {
        if (sqlSession == null) {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            sqlSession = sqlSessionFactory.openSession(true);
        }
        return sqlSession;
    }
}
