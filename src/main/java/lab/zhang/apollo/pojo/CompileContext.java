package lab.zhang.apollo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class CompileContext {

    private int level;

    private Set<Operand<?, String>> requiredOperandSet;

    protected List<List<Operation<?, ?>>> operationList;


    public CompileContext(int level) {
        this.level = level;
        this.requiredOperandSet = new HashSet<>();
        this.operationList = new ArrayList<>();
    }

    public CompileContext() {
        this(0);
    }

    public CompileContext incrLevel() {
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
