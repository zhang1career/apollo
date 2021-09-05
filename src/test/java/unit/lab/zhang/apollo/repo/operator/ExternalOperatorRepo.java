package unit.lab.zhang.apollo.repo.operator;

import unit.lab.zhang.apollo.dao.ExternalOperatorDao;
import lab.zhang.apollo.entity.ExternalOperatorEntity;
import lab.zhang.apollo.exception.RunnableCodeException;
import lab.zhang.apollo.repo.StorableOperator;
import unit.lab.zhang.apollo.repo.BaseRepo;

import java.io.IOException;

/**
 * @author zhangrj
 */
public class ExternalOperatorRepo extends BaseRepo implements StorableOperator {

    private ExternalOperatorDao externalOperatorDao;


    public ExternalOperatorRepo() {
        try {
            externalOperatorDao = getMapper(ExternalOperatorDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExternalOperatorEntity getItem(long id) {
        return externalOperatorDao.findOne(id);
    }

    public boolean isExist(long id) {
        return externalOperatorDao.isExist(id);
    }

    @Override
    public String getClazz(long id) {
        ExternalOperatorEntity externalOperatorEntity = getItem(id);
        if (externalOperatorEntity == null) {
            throw new RunnableCodeException("The external operator is not found, id=" + id);
        }
        return externalOperatorEntity.getClazz();
    }
}
