package unit.lab.zhang.apollo.pojo.operators.externals;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.ParamContext;
import lab.zhang.apollo.pojo.operators.externals.ExternalOperator;

import java.util.List;

public class ExternalOperatorAbout extends ExternalOperator {
    ExternalOperatorAbout() {
        super();
    }

    @Override
    public Object doCalc(List<? extends Valuable<Object>> operands, ParamContext paramContext) {
        return "Welcome to Apollo, a calculation center";
    }
}
