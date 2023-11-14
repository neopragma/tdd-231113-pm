package com.neopragma.rpn;

import java.util.Stack;

public class RPN
{
    private final Stack<String> stack;
    public RPN() {
        stack = new Stack<String>();
    }

    public String enter(String token) {
        if (null == token) {
            throw new NullPointerException("Token cannot be null");
        }
        if (token.equals("+") || token.equals("*") || token.equals("-") || token.equals("/") ){
            if (stack.size() == 2) {
                double value1 = Double.parseDouble(stack.pop());
                double value2 = Double.parseDouble(stack.pop());
                switch(token) {
                    case "+":
                        stack.push(String.valueOf(value1 + value2));
                        break;
                    case "*":
                        stack.push(String.valueOf(value1 * value2));
                        break;
                    case "-":
                        stack.push(String.valueOf(value1 - value2));
                        break;
                    default:
                        stack.push(String.valueOf(value1 / value2));
                }
            } else {
                throw new RuntimeException("Please enter another number");
            }
        } else {
            try {
                Double.parseDouble(token);
            } catch (NumberFormatException okException) {
                throw new RuntimeException("Token entered is neither a number nor an operator");
            } catch (Exception badException) {
                throw new RuntimeException("We don't know what happened here");
            }
            stack.push(token);
        }
        return stack.peek();
    }

}