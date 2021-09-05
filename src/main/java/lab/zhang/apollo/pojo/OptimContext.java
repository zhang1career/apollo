package lab.zhang.apollo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class OptimContext {

    private int level;

    private Map<Integer, Operand<?, String>> indexMap;

    protected List<List<Operation<?, ?>>> operationList;


    public OptimContext(int level) {
        this.level = level;
        this.indexMap = new HashMap<>();
        this.operationList = new ArrayList<>();
    }

    public OptimContext() {
        this(0);
    }

    public OptimContext incrLevel() {
        level++;
        return this;
    }

    public int getOperationListSize() {
        return operationList.size();
    }

    public List<Operation<?, ?>> getOperationListOfLevel(int level) {
        enlargeOperationList(level);
        return operationList.get(level);
    }

    private void enlargeOperationList(int level) {
        if (operationList == null) {
            operationList = new ArrayList<>();
        }
        while (operationList.size() < level + 1) {
            operationList.add(new ArrayList<>());
        }
    }
}
