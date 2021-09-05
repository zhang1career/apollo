package unit.lab.zhang.apollo.repo.expression;

import unit.lab.zhang.apollo.dao.PlannedExpressionDao;
import unit.lab.zhang.apollo.entity.PlannedExpressionEntity;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.repo.StorableExpression;
import lab.zhang.apollo.service.LexerService;
import lab.zhang.apollo.service.lexer.BasicLexerService;
import org.jetbrains.annotations.Nullable;
import unit.lab.zhang.apollo.repo.BaseRepo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
public class PlannedExpressionRepo extends BaseRepo implements StorableExpression {

    private PlannedExpressionDao plannedExpressionDao;
    private LexerService lexerService;

    public PlannedExpressionRepo() {
        try {
            plannedExpressionDao = getMapper(PlannedExpressionDao.class);
            lexerService = new BasicLexerService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public List<PlannedExpressionEntity> getList(List<Long> idList) {
        return plannedExpressionDao.findAllByIdList(idList);
    }

    public PlannedExpressionEntity getItem(long id) {
        return plannedExpressionDao.findOne(id);
    }

    @Override
    public String getExpression(long id) {
        PlannedExpressionEntity plannedExpressionEntity = getItem(id);
        if (plannedExpressionEntity == null) {
            return null;
        }
        return plannedExpressionEntity.getExpression();
    }

    public Map<Long, Token> getTokenListIndexById(List<Long> idList) {
        List<PlannedExpressionEntity> plannedExpressionEntityList = getList(idList);
        if (plannedExpressionEntityList == null) {
            return null;
        }

        Map<Long, PlannedExpressionEntity> plannedOperationEntityMap = indexById(plannedExpressionEntityList);
        return columnOf(plannedOperationEntityMap, p -> lexerService.tokenOf(p.getExpression()));
    }

    public long create(long operationId, Token node) {
        PlannedExpressionEntity plannedExpressionEntity = new PlannedExpressionEntity(operationId, lexerService.jsonOf(node));
        plannedExpressionDao.create(plannedExpressionEntity);
        return plannedExpressionDao.getLastInsertId();
    }
}
