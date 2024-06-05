package calculator.operators;

import calculator.evaluator.Operand;

public class DivideOperator extends Operator
{
    public int priority()
    {
        return 2;
    }

    public Operand execute(Operand operandOne, Operand operandTwo)
    {
        Operand temp;
        temp = new Operand(operandOne.getValue() / operandTwo.getValue());
        return temp;
    }
}
