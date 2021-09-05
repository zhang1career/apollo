package lab.zhang.apollo.entity;

import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class ExpressionEntity extends BaseEntity {

    private String expression;

    public ExpressionEntity(long id, String expression) {
        this.id = id;
        this.expression = expression;
    }
}
