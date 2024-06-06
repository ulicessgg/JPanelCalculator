package calculator.operators;

import calculator.evaluator.Operand;

public class ClosedPOperator extends Operator{
    public int priority()
    {
        return 0;
    }

    public Operand execute(Operand operandOne, Operand operandTwo)
    {
        return null;
    }
}
