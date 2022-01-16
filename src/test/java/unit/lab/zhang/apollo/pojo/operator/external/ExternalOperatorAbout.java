package unit.lab.zhang.apollo.pojo.operator.external;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.ExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operator.external.ExternalOperator;

import java.util.List;

public class ExternalOperatorAbout extends ExternalOperator {
    ExternalOperatorAbout() {
        super();
    }

    @Override
    public Object doCalc(List<? extends Valuable<Object>> operands, ParamContext paramContext, ExeConfig exeConfig) {
        return "Welcome to Apollo, a calculation center";
    }
}
