package lab.zhang.apollo.service;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.util.CastUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangrj
 */
public class ParseService {
    private final StorableOperator storableOperator;

    public ParseService(StorableOperator storableOperator) {
        this.storableOperator = storableOperator;
    }

    public Valuable<?> valuableOf(@NotNull Token token) throws ExecutionException {
        Valuable<?> valuable;

        if (token.getType().getOpType() == ApolloType.OpType.OPERAND) {
            valuable = token.getType().valuableOf(null, 0, CastUtil.from(token.getValue()));
            return valuable;
        }

        valuable = token.getType().valuableOf(storableOperator, token.getId(), null);
        List<Valuable<?>> childValueList = new ArrayList<>();
        List<Token> childTokenList = CastUtil.from(token.getValue());
        if (childTokenList == null) {
            return valuable;
        }

        for (Token childToken : childTokenList) {
            childValueList.add(valuableOf(childToken));
        }
        ((Operation<?, ?>) valuable).setChildren(CastUtil.from(childValueList));

        ((Operation<?, ?>) valuable).postParse();
        return valuable;
    }
}
