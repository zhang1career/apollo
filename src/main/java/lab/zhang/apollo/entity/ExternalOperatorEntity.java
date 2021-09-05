package lab.zhang.apollo.entity;

import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class ExternalOperatorEntity extends BaseEntity {

    private String clazz;

    public ExternalOperatorEntity(long id, String clazz) {
        this.id = id;
        this.clazz = clazz;
    }
}
