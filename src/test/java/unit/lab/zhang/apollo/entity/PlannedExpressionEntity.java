package unit.lab.zhang.apollo.entity;

import lab.zhang.apollo.entity.BaseEntity;
import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class PlannedExpressionEntity extends BaseEntity {

    private long expressionId;

    private String expression;


    public PlannedExpressionEntity(Long id, Long expressionId, String expression) {
        this.id = id;
        this.expressionId = expressionId;
        this.expression = expression;
    }


    public PlannedExpressionEntity(Long expressionId, String expression) {
        this(null, expressionId, expression);
    }
}
