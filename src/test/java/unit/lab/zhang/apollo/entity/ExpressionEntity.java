package unit.lab.zhang.apollo.entity;

import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class ExpressionEntity extends BaseEntity {

    private String name;
    private String expression;

    public ExpressionEntity(long id, String name, String expression) {
        this.id = id;
        this.name = name;
        this.expression = expression;
    }
}
