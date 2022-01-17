package lab.zhang.apollo.pojo.context;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.Operand;
import lab.zhang.apollo.pojo.operand.Variable;
import lab.zhang.apollo.pojo.Operation;
import lab.zhang.apollo.util.CastUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangrj
 */
@Getter
@Setter
public class CompileContext {

    private int level;

    private Set<Operand<?, String>> requiredOperandSet;

    private List<List<Valuable<?>>> parallelOperationList;

    private List<Valuable<?>> complementaryValuableList;

    private Operation<?, ?> originalOperation;

    public CompileContext(int level) {
        this.level = level;
        this.requiredOperandSet = new HashSet<>();
        this.parallelOperationList = new ArrayList<>();
        this.complementaryValuableList = new ArrayList<>();
    }

    public CompileContext() {
        this(0);
    }

    public CompileContext incrLevel() {
        level++;
        return this;
    }

    public int getPrimaryOperationListSize() {
        return parallelOperationList.size();
    }

    public List<Valuable<?>> getPrimaryOperationListOfLevel(int level) {
         enlargePrimaryOperationList(level);
        return parallelOperationList.get(level);
    }

    public void requiredOperandSetAddAll(List<? extends Valuable<?>> valuableList) {
        for (Valuable<?> valuable : valuableList) {
            if (!(valuable instanceof Variable)) {
                continue;
            }
            requiredOperandSet.add(CastUtil.from(valuable));
        }
    }

    private void enlargePrimaryOperationList(int level) {
        if (parallelOperationList == null) {
            parallelOperationList = new ArrayList<>();
        }
        while (parallelOperationList.size() < level + 1) {
            parallelOperationList.add(new ArrayList<>());
        }
    }
}
