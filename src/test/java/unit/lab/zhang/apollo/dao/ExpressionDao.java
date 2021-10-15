package unit.lab.zhang.apollo.dao;

import org.apache.ibatis.annotations.Select;
import unit.lab.zhang.apollo.entity.ExpressionEntity;

/**
 * @author zhangrj
 */

public interface ExpressionDao {

    @Select("SELECT * FROM expression WHERE id=#{id}")
    ExpressionEntity findOne(long id);
}
