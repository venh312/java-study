package effective.item42;

import java.util.function.DoubleBinaryOperator;

public enum OperationLambda {
    PLUS("+", (x, y) -> x + y),
    MINUS("+", (x, y) -> x - y),
    TIMES("+", (x, y) -> x * y),
    DIVIDE("+", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    OperationLambda(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override public String toString() { return symbol; }

    public double apply(double x, double y) {
        return op.applyAsDouble(x,y);
    };
}
