package lab.zhang.apollo.pojo;

import lab.zhang.apollo.bo.Valuable;
import lab.zhang.apollo.exception.RunnableCodeException;
import lab.zhang.apollo.pojo.operands.instants.*;
import lab.zhang.apollo.pojo.operands.variables.*;
import lab.zhang.apollo.pojo.operations.SortedOperation;
import lab.zhang.apollo.pojo.operations.UnsortedOperation;
import lab.zhang.apollo.pojo.operators.arithmetics.Addition;
import lab.zhang.apollo.pojo.operators.arithmetics.Subtraction;
import lab.zhang.apollo.pojo.operators.comparators.*;
import lab.zhang.apollo.pojo.operators.externals.ExternalOperator;
import lab.zhang.apollo.pojo.operators.logics.*;
import lab.zhang.apollo.repo.StorableOperator;
import lab.zhang.apollo.util.CastUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
        }

        @Override
        public Valuable<Boolean> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantBool.of(CastUtil.from(value));
        }
    },
    /**
     * integer instant operand
     */
    INSTANT_INT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
        }

        @Override
        public Valuable<Integer> valuableOf(StorableOperator storableOperator, long id, Object value) {
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
            return new HashSet<>(APOLLO_TYPE_LIST_STR);
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
    INSTANT_ARRAY {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantArray.of(CastUtil.from(value));

        }
    },
    INSTANT_MAP {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return InstantMap.of(CastUtil.from(value));
        }
    },
    INSTANT_OBJECT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
        }

        @Override
        public Valuable<Integer> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableInt.of(CastUtil.from(value));
        }
    },
    VARIABLE_LONG {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_LONG);
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
            return new HashSet<>(APOLLO_TYPE_LIST_STR);
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
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableArray.of(CastUtil.from(value));
        }
    },
    VARIABLE_MAP {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return VariableMap.of(CastUtil.from(value));
        }
    },
    VARIABLE_OBJECT {
        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
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
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
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
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
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
            return op1.getValue(paramContext) != 0;
        }

        @Override
        public Set<ApolloType> getPairableOperandTypes() {
            return new HashSet<>(APOLLO_TYPE_LIST_INT);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_NUM);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_BOOL);
        }

        @Override
        public Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return UnsortedOperation.of(LogicalNot.of());
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
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
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
            return new HashSet<>(APOLLO_TYPE_LIST_ALL);
        }

        @Override
        public Valuable<Object> valuableOf(StorableOperator storableOperator, long id, Object value) {
            return null;
        }
    },
    ;

    static private final List<ApolloType> APOLLO_TYPE_LIST_BOOL = Arrays.asList(
            INSTANT_BOOL,   VARIABLE_BOOL,
            EQUAL_TO, NOT_EQUAL_TO, SMALLER_THAN, SMALLER_THAN_OR_EQUAL_TO, GREATER_THAN, GREATER_THAN_OR_EQUAL_TO,
            LOGICAL_EQUAL_TO, LOGICAL_NOT_EQUAL_TO, LOGICAL_AND, LOGICAL_OR, LOGICAL_NOT);

    static private final List<ApolloType> APOLLO_TYPE_LIST_INT = Arrays.asList(
            INSTANT_INT,    VARIABLE_INT,
            ADDITION_INT, SUBTRACTION_INT, MULTIPLICATION_INT, DIVISION_INT);

    static private final List<ApolloType> APOLLO_TYPE_LIST_LONG = Arrays.asList(
            INSTANT_LONG,   VARIABLE_LONG);

    static private final List<ApolloType> APOLLO_TYPE_LIST_NUM = APOLLO_TYPE_LIST_INT;

    static private final List<ApolloType> APOLLO_TYPE_LIST_STR = Arrays.asList(
            INSTANT_STR, VARIABLE_STR);

    static private final List<ApolloType> APOLLO_TYPE_LIST_ALL = Arrays.asList(
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
            EXTERNAL_OPERATOR,
            ORIGINAL_OPERATION);


    static Map<Integer, Integer> uuidMap;

    static {
        uuidMap = new HashMap<>();
        uuidMap.put(INSTANT_BOOL.id,                0x6914B8C1);
        uuidMap.put(INSTANT_INT.id,                 0x35AA1F69);
        uuidMap.put(INSTANT_LONG.id,                0x39C7E382);
        uuidMap.put(INSTANT_STR.id,                 0x723C7BAB);
        uuidMap.put(INSTANT_ARRAY.id,               0x0D27952E);
        uuidMap.put(INSTANT_MAP.id,                 0x56B8DFB9);

        uuidMap.put(VARIABLE_BOOL.id,               0x1C3BFC50);
        uuidMap.put(VARIABLE_INT.id,                0x41165CAC);
        uuidMap.put(VARIABLE_LONG.id,               0x6AD58E94);
        uuidMap.put(VARIABLE_STR.id,                0x66A27177);
        uuidMap.put(VARIABLE_ARRAY.id,              0x18A01452);
        uuidMap.put(VARIABLE_MAP.id,                0x289A1041);

        uuidMap.put(ADDITION_INT.id,                0x46EA16A4);
        uuidMap.put(SUBTRACTION_INT.id,             0x07027B67);
        uuidMap.put(MULTIPLICATION_INT.id,          0x6AB980A9);
        uuidMap.put(DIVISION_INT.id,                0x1685B7DE);

        uuidMap.put(EQUAL_TO.id,                    0x1DE43694);
        uuidMap.put(NOT_EQUAL_TO.id,                0x6DFE3550);
        uuidMap.put(SMALLER_THAN.id,                0x4074D178);
        uuidMap.put(SMALLER_THAN_OR_EQUAL_TO.id,    0x0FDA554A);
        uuidMap.put(GREATER_THAN.id,                0x24DB35CA);
        uuidMap.put(GREATER_THAN_OR_EQUAL_TO.id,    0x5834D92F);

        uuidMap.put(LOGICAL_EQUAL_TO.id,            0x26AC5F0A);
        uuidMap.put(LOGICAL_NOT_EQUAL_TO.id,        0x7905DF2F);
        uuidMap.put(LOGICAL_AND.id,                 0x034520E6);
        uuidMap.put(LOGICAL_OR.id,                  0x06ED8409);
        uuidMap.put(LOGICAL_NOT.id,                 0x7D3ADCBA);

        uuidMap.put(EXTERNAL_OPERATOR.id,           0x771B821A);
    }

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

    int getUuid() {
        if (!uuidMap.containsKey(id)) {
            throw new RunnableCodeException("not found in uuidMap, key=" + id);
        }
        return uuidMap.get(id);
    }

    public OpType getOpType() {
        return OpType.OPERAND;
    }

    public boolean checkType(@NotNull List<ApolloType> types) {
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

    public abstract Valuable<?> valuableOf(StorableOperator storableOperator, long id, Object value);

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
