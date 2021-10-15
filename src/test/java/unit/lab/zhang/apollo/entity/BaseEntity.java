package unit.lab.zhang.apollo.entity;

import lab.zhang.apollo.entity.Identical;

/**
 * @author zhangrj
 */
abstract public class BaseEntity implements Identical {
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }
}
