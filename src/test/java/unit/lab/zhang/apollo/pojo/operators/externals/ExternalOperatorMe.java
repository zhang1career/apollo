package unit.lab.zhang.apollo.pojo.operators.externals;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operators.externals.ExternalOperator;

import java.util.List;

public class ExternalOperatorMe extends ExternalOperator {
    ExternalOperatorMe() {
        super();
    }

    @Override
    public Object doCalc(List<? extends Valuable<Object>> operands, ParamContext paramContext) {
        return "zhangrj";
    }
}
