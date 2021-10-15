package unit.lab.zhang.apollo.repo.expression;

import unit.lab.zhang.apollo.dao.ExpressionDao;
import unit.lab.zhang.apollo.entity.ExpressionEntity;
import unit.lab.zhang.apollo.repo.BaseRepo;

import java.io.IOException;

/**
 * @author zhangrj
 */
public class ExpressionRepo extends BaseRepo {

    private ExpressionDao expressionDao;

    public ExpressionRepo() {
        try {
            expressionDao = getMapper(ExpressionDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExpressionEntity getItem(long id) {
        return expressionDao.findOne(id);
    }
}