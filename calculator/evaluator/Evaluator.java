package calculator.evaluator;

import calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private final Stack<Operand> operandStack;
  private final Stack<Operator> operatorStack;


  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression ) throws InvalidTokenException {
    String expressionToken;
    StringTokenizer expressionTokenizer;
    String delimiters = " +/*-^()";

    // 3 arguments tells the tokenizer to return delimiters as tokens
    expressionTokenizer = new StringTokenizer( expression, delimiters, true );

    while ( expressionTokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( expressionToken = expressionTokenizer.nextToken() ).equals( " " ))
      {
        // check if token is an operand
        if ( Operand.check( expressionToken ))
        {
          operandStack.push( new Operand( expressionToken ));
        }
        else if(expressionToken.equals("("))
        {
          operatorStack.push(new OpenPOperator());
        }
        else if(expressionToken.equals(")"))
        {
          while (!operatorStack.isEmpty() && !(operatorStack.peek() instanceof OpenPOperator ))
          {
            Operator operatorFromStack = operatorStack.pop();
            Operand operandTwo = operandStack.pop();
            Operand operandOne = operandStack.pop();
            Operand result = operatorFromStack.execute( operandOne, operandTwo );
            operandStack.push( result );
          }
          operatorStack.pop();
        }
        else
        {
          if ( ! Operator.check( expressionToken ))
          {
            throw new InvalidTokenException(expressionToken);
          }

          // TODO fix this line of code.
          Operator newOperator = Operator.getOperator(expressionToken);
          // I fixed it now it should return the right operator based off the token. hopefully


            while (!operatorStack.isEmpty() && operatorStack.peek().priority() >= newOperator.priority() )
            {
              Operator operatorFromStack = operatorStack.pop();
              Operand operandTwo = operandStack.pop();
              Operand operandOne = operandStack.pop();
              Operand result = operatorFromStack.execute( operandOne, operandTwo );
              operandStack.push( result );
            }

            operatorStack.push( newOperator );
          
        }
      }
    }

    while(!operatorStack.isEmpty())
    {
      Operator operatorFromStack = operatorStack.pop();
      Operand operandTwo = operandStack.pop();
      Operand operandOne = operandStack.pop();
      Operand result = operatorFromStack.execute( operandOne, operandTwo );
      operandStack.push( result );
    }

    if(operandStack.isEmpty())
    {
      throw new InvalidTokenException("Error");
    }

    /*
     * once no more tokens need to be scanned from StringTokenizer,
     * we need to evaluate the remaining sub-expressions.
     */
    return operandStack.pop().getValue();
  }

  public static void main(String[] args) throws InvalidTokenException {
     if(args.length == 1){
      Evaluator e = new Evaluator();
      System.out.println(e.evaluateExpression(args[0]));
     }else{
      System.err.println("Invalid arguments or No expression given");
     }
  }

}
