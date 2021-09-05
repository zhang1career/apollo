package unit.lab.zhang.apollo.dao;

import lab.zhang.apollo.entity.ExternalOperatorEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangrj
 */
@Mapper
public interface ExternalOperatorDao {

    @Select("SELECT * FROM external_operator WHERE id=#{id}")
    ExternalOperatorEntity findOne(@Param("id") long id);


    @Select("SELECT EXISTS(SELECT 1 FROM external_operator WHERE id=#{id})")
    boolean isExist(@Param("id") long id);
}