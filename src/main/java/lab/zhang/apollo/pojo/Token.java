package lab.zhang.apollo.pojo;

import com.alibaba.fastjson.serializer.ValueFilter;
import lab.zhang.apollo.exception.TokenizationException;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


/**
 * @author zhangrj
 */
@Data
public class Token {
    static private final String TYPE = "type";

    @NotNull
    @Contract(pure = true)
    static public ValueFilter getFilter() {
        return (object, name, value) -> {
            try {
                if (!TYPE.equals(name) || !(value instanceof ApolloType)) {
                    return value;
                }
                return ((ApolloType) value).getId();
            } catch (Exception e) {
                return value;
            }
        };
    }


    private String name;

    private ApolloType type;

    private long id;

    private Object value;


    public Token() {
    }

    public Token(String name, ApolloType type, long id, Object value) {
        if (type == null) {
            throw new TokenizationException("The type does not exist");
        }
        this.name = name;
        this.type = type;
        this.id = id;
        this.value = value;
    }

    public Token(String name, ApolloType type, Object value) {
        this(name, type, 0, value);
    }

    public void setType(ApolloType type) {
        if (type == null) {
            throw new TokenizationException("The type does not exist");
        }
        this.type = type;
    }
}
