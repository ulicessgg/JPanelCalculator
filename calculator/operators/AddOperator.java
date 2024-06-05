package calculator.operators;

import calculator.evaluator.Operand;

public class AddOperator extends Operator{
    public int priority()
    {
        return 1;
    }

    public Operand execute(Operand operandOne, Operand operandTwo)
    {
        Operand temp;
        temp = new Operand(operandOne.getValue() + operandTwo.getValue());
        return temp;
    }
}
