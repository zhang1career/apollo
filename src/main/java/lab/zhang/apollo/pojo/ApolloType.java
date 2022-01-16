package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.pojo.cofig.instance.DummyExeConfig;
import lab.zhang.apollo.pojo.context.ParamContext;
import lab.zhang.apollo.pojo.operand.instant.*;
import lab.zhang.apollo.pojo.operand.variable.*;
import lab.zhang.apollo.pojo.operation.SortedOperation;
import lab.zhang.apollo.pojo.operation.UnsortedOperation;
import lab.zhang.apollo.pojo.operator.arithmetic.Addition;
import lab.zhang.apollo.pojo.operator.arithmetic.Subtraction;
import lab.zhang.apollo.pojo.operator.comparator.*;
import lab.zhang.apollo.pojo.operator.external.ExternalOperator;
import lab.zhang.apollo.pojo.operator.logic.*;
import lab.zhang.apollo.pojo.operator.string.StringRegMatch;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.util.CastUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangrj
 */
@Getter
public enum ApolloType {
    /**
     * boolean instant operand
     */
    INSTANT_BOOL {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<Boolean> valuableOf(StorableOperator storableOperator, long id, Object value) throws ExecutionException {
            return InstantBool.of(CastUtil.from(value));
        }
    },
    /**
     * integer instant operand
     */
    INSTANT_INT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<Integer> valuableOf(StorableOperator storableOperator, long id, Object value) throws ExecutionException {
            return InstantInt.of(CastUtil.from(value));
        }
    },
    INSTANT_LONG {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    INSTANT_DOUBLE {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    INSTANT_CHAR {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    /**
     * string instant operand
     */
    INSTANT_STR {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_STR;
        }

        @Override
        public Valuable<String> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantStr.of(CastUtil.from(value));
        }
    },
    INSTANT_BYTES {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    /**
     * array instant operand
     */
    INSTANT_ARRAY {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantArray.of(CastUtil.from(value));
        }
    },
    /**
     * map instant operand
     */
    INSTANT_MAP {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantMap.of(CastUtil.from(value));
        }
    },
    /**
     * object instant operand
     */
    INSTANT_OBJECT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantObject.of(CastUtil.from(value));
        }
    },

    /**
     * boolean variable operand
     */
    VARIABLE_BOOL {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<Boolean> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableBool.of(CastUtil.from(value));
        }
    },
    /**
     * integer variable operand
     */
    VARIABLE_INT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<Integer> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableInt.of(CastUtil.from(value));
        }
    },
    VARIABLE_LONG {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_LONG;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableLong.of(CastUtil.from(value));

        }
    },
    VARIABLE_DOUBLE {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    VARIABLE_CHAR {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    /**
     * string variable operand
     */
    VARIABLE_STR {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_STR;
        }

        @Override
        public Valuable<String> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableStr.of(CastUtil.from(value));
        }
    },
    VARIABLE_BYTES {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(Collections.emptyList());
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    VARIABLE_ARRAY {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableArray.of(CastUtil.from(value));
        }
    },
    VARIABLE_MAP {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableMap.of(CastUtil.from(value));
        }
    },
    VARIABLE_OBJECT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableObject.of(CastUtil.from(value));
        }
    },


    /**
     * addition operator
     */
    ADDITION_INT {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.MULTINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return SortedOperation.of(Addition.of());
        }
    },

    /**
     * subtraction operator
     */
    SUBTRACTION_INT {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(Subtraction.of());
        }
    },
    MULTIPLICATION_INT {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.MULTINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    DIVISION_INT {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public boolean checkValue(List<? extends Valuable<?>> operands, ParamContext paramContext) {
            Valuable<Integer> op1 = CastUtil.from(operands.get(1));
            return op1.getValue(paramContext, DummyExeConfig.of()) != 0;
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_INT;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },

    /**
     * equal-to operator
     */
    EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(EqualTo.of());
        }
    },
    NOT_EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(NotEqualTo.of());
        }
    },
    SMALLER_THAN {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(SmallerThan.of());
        }
    },
    SMALLER_THAN_OR_EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(SmallerThanOrEqualTo.of());
        }
    },

    /**
     * greater-than operator
     */
    GREATER_THAN {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(GreaterThan.of());
        }
    },
    GREATER_THAN_OR_EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_NUM;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(SmallerThanOrEqualTo.of());
        }
    },

    /**
     * logical equal-to operator
     */
    LOGICAL_EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return SortedOperation.of(LogicalEqualTo.of());
        }
    },

    /**
     * logical not-equal-to operator
     */
    LOGICAL_NOT_EQUAL_TO {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return SortedOperation.of(LogicalNotEqualTo.of());
        }
    },

    /**
     * logical and operator
     */
    LOGICAL_AND {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.MULTINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return SortedOperation.of(LogicalAnd.of());
        }
    },

    /**
     * logical or operator
     */
    LOGICAL_OR {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.MULTINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return SortedOperation.of(LogicalOr.of());
        }
    },

    /**
     * logical not operator
     */
    LOGICAL_NOT {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.UNARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_BOOL;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(LogicalNot.of());
        }
    },

    /**
     * string regex match operator
     */
    STRING_REG_MATCH {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.BINARY.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_STR;
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(StringRegMatch.of());
        }
    },

    /**
     * external operator
     */
    EXTERNAL_OPERATOR {
        @Override
        public OpType getOpType() {
            return OpType.OPERATOR;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.NO_CHECK.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<Object> valuableOf(StorableOperator storableOperator, long id, Object value) {
            Valuable<Object> obj = null;
            try {
                obj = UnsortedOperation.of(ExternalOperator.of(storableOperator, id));
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }
    },


    /**
     * origin operation
     */
    ORIGINAL_OPERATION {
        @Override
        public OpType getOpType() {
            return OpType.OPERATION;
        }

        @Override
        public boolean checkCard(int num) {
            return Cardinality.NO_CHECK.checkCard(num);
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return APOLLO_TYPE_SET_ALL;
        }

        @Override
        public Valuable<Object> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    ;

    //@todo list to array
    static private final Set<ApolloType> APOLLO_TYPE_SET_BOOL = new HashSet<>(Arrays.asList(
            INSTANT_BOOL,   VARIABLE_BOOL,
            EQUAL_TO, NOT_EQUAL_TO, SMALLER_THAN, SMALLER_THAN_OR_EQUAL_TO, GREATER_THAN, GREATER_THAN_OR_EQUAL_TO,
            LOGICAL_EQUAL_TO, LOGICAL_NOT_EQUAL_TO, LOGICAL_AND, LOGICAL_OR, LOGICAL_NOT
    ));

    static private final Set<ApolloType> APOLLO_TYPE_SET_INT = new HashSet<>(Arrays.asList(
            INSTANT_INT,    VARIABLE_INT,
            ADDITION_INT, SUBTRACTION_INT, MULTIPLICATION_INT, DIVISION_INT
    ));

    static private final Set<ApolloType> APOLLO_TYPE_SET_LONG = new HashSet<>(Arrays.asList(
            INSTANT_LONG,   VARIABLE_LONG
    ));

    static private final Set<ApolloType> APOLLO_TYPE_SET_NUM = APOLLO_TYPE_SET_INT;

    static private final Set<ApolloType> APOLLO_TYPE_SET_STR = new HashSet<>(Arrays.asList(
            INSTANT_STR, VARIABLE_STR,
            STRING_REG_MATCH
    ));

    static private final Set<ApolloType> APOLLO_TYPE_SET_ALL = new HashSet<>(Arrays.asList(
            INSTANT_BOOL,   VARIABLE_BOOL,
            INSTANT_INT,    VARIABLE_INT,
            INSTANT_LONG,   VARIABLE_LONG,
            INSTANT_STR,    VARIABLE_STR,
            INSTANT_ARRAY,  VARIABLE_ARRAY,
            INSTANT_MAP,    VARIABLE_MAP,
            INSTANT_OBJECT, VARIABLE_OBJECT,
            EQUAL_TO, NOT_EQUAL_TO, SMALLER_THAN, SMALLER_THAN_OR_EQUAL_TO, GREATER_THAN, GREATER_THAN_OR_EQUAL_TO,
            LOGICAL_EQUAL_TO, LOGICAL_NOT_EQUAL_TO, LOGICAL_AND, LOGICAL_OR, LOGICAL_NOT,
            ADDITION_INT, SUBTRACTION_INT, MULTIPLICATION_INT, DIVISION_INT,
            STRING_REG_MATCH,
            EXTERNAL_OPERATOR,
            ORIGINAL_OPERATION
    ));


    private final int id;

    private static class IdCounter {
        private static int nextValue = 0;
    }

    ApolloType(int id) {
        this.id = id;
        IdCounter.nextValue = id + 1;
    }

    ApolloType() {
        this(IdCounter.nextValue);
    }

    public OpType getOpType() {
        return OpType.OPERAND;
    }

    public boolean checkType(@NotNull Set<ApolloType> types) {
        if (types.size() <= 0) {
            return true;
        }
        Set<ApolloType> pairableTypes = this.getPairableOperandTypes();
        for (ApolloType type : types) {
            if (!pairableTypes.contains(type)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCard(int num) {
        return Cardinality.NONE.checkCard(num);
    }

    public boolean checkValue(List<? extends Valuable<?>> operands, ParamContext paramContext) {
        return true;
    }

    public abstract Set<ApolloType> getPairableOperandTypes();

    public abstract Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) throws ExecutionException;

    @Override
    public String toString() {
        return String.valueOf(id);
    }


    public enum OpType {
        /**
         * operand
         */
        OPERAND,

        /**
         * operator
         */
        OPERATOR,

        /**
         * operation
         */
        OPERATION,
    }


    /**
     *
     */
    @Getter
    @AllArgsConstructor
    public enum Cardinality {
        /**
         * any
         */
        NO_CHECK(-1) {
            @Override
            public boolean checkCard(int num) {
                return true;
            }
        },

        /**
         * none
         */
        NONE(0),

        /**
         * one
         */
        UNARY(1),

        /**
         * two
         */
        BINARY(2),

        /**
         * more
         */
        MULTINARY(Integer.MAX_VALUE) {
            @Override
            public boolean checkCard(int num) {
                return num > 0;
            }
        },
        ;

        private final int card;

        public boolean checkCard(int num) {
            return card == num;
        }
    }
}
