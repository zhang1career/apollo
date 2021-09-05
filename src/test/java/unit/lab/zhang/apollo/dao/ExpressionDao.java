package unit.lab.zhang.apollo.dao;

import lab.zhang.apollo.entity.ExpressionEntity;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangrj
 */

public interface ExpressionDao {

    @Select("SELECT * FROM expression WHERE id=#{id}")
    ExpressionEntity findOne(long id);
}
