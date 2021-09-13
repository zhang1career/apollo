package lab.zhang.apollo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lab.zhang.apollo.exception.TokenizationException;
import lab.zhang.apollo.pojo.ApolloType;
import lab.zhang.apollo.pojo.Token;
import lab.zhang.apollo.util.StrUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author zhangrj
 */
abstract public class LexerService {

    public List<Token> tokenListOf(String cond) {
        if (StrUtil.isNill(cond)) {
            return null;
        }
        return JSON.parseArray(cond, Token.class);
    }

    public Token tokenOf(String cond) {
        if (cond == null || cond.length() <= 0) {
            return null;
        }

        Token token = JSON.parseObject(cond, Token.class);
        // check name
        if (token.getName() == null || "".equals(token.getName())) {
            throw new TokenizationException("The name is missing or empty");
        }
        // check type
        if (token.getType() == null) {
            throw new TokenizationException("The type is missing");
        }
        // check id
        if (!checkId(token)) {
            throw new TokenizationException("The id has problem...");
        }
        // check value
        if (token.getValue() == null) {
            return token;
        }
        if (!(token.getValue() instanceof JSONArray)) {
            return token;
        }

        List<Token> childTokenList = new ArrayList<>();
        for (Object childCond : (JSONArray) token.getValue()) {
            if (childCond == null || childCond.toString().isEmpty()) {
                continue;
            }
            childTokenList.add(tokenOf(childCond.toString()));
        }
        // check children cardinality
        if (!token.getType().checkCard(childTokenList.size())) {
            throw new TokenizationException("The num of operands is wrong. type=" + token.getType() + ", card=" + childTokenList.size());
        }
        // check children type
        List<ApolloType> typeList = getApolloTypeList(childTokenList);
        if (!token.getType().checkType(typeList)) {
            throw new TokenizationException("The type of operands is wrong. type=" + token.getType() + ", typeList=" + typeList);
        }
        token.setValue(childTokenList);

        return token;
    }

    /**
     * check token id, which is used in the user-defined operator
     * @param token the token to be checked
     * @return true - pass, false - failed
     */
    abstract protected boolean checkId(Token token);

    public String jsonOf(Token token) {
        if (token == null) {
            return null;
        }
        return toJSONString(token, Token.getFilter(), SerializerFeature.WriteMapNullValue);
    }

    public String jsonOf(Token[] tokens) {
        if (tokens == null) {
            return null;
        }
        return toJSONString(tokens, Token.getFilter(), SerializerFeature.WriteMapNullValue);
    }

    @NotNull
    private List<ApolloType> getApolloTypeList(@NotNull List<Token> childrenToken) {
        List<ApolloType> types = new ArrayList<>();
        for (Token childToken : childrenToken) {
            types.add(childToken.getType());
        }
        return types;
    }
}
