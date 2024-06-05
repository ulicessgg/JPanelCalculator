package calculator.operators;

import calculator.evaluator.Operand;

public class PowerOperator extends Operator
{
    public int priority()
    {
        return 3;
    }

    public Operand execute(Operand operandOne, Operand operandTwo)
    {
        Operand temp;
        temp = new Operand((int)Math.pow(operandOne.getValue(), operandTwo.getValue()));
        return temp;
    }
}
