package unit.lab.zhang.apollo.dao;

import unit.lab.zhang.apollo.entity.PlannedExpressionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author zhangrj
 */
@Mapper
public interface PlannedExpressionDao {

    @Select("SELECT * FROM planned_expression WHERE id=#{id}")
    PlannedExpressionEntity findOne(@Param("id") long id);


    @Select({
            "<script>",
                "SELECT", "*",
                "FROM planned_expression",
                "WHERE id IN",
                "<foreach collection='idList' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
            "</script>"
    })
    List<PlannedExpressionEntity> findAllByIdList(@Param("idList") List<Long> idList);


    @Update("INSERT INTO planned_expression (expression_id,value) VALUES (#{expressionId},#{value})")
    void create(PlannedExpressionEntity plannedExpressionEntity);


    @Select("SELECT LAST_INSERT_ID() FROM planned_expression")
    long getLastInsertId();
}