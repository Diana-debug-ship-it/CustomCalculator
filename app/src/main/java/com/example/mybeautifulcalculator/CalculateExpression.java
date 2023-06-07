package com.example.mybeautifulcalculator;

import java.util.Stack;

public class CalculateExpression {

    public static double getResult(String s) {
        String rpn = expressionToRPN(addingNulls(s));
        return RPNtoResult(rpn);
    }
    private static String expressionToRPN(String expression) { //RPN - Reverse polish notation
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i=0; i<expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if (priority==0){
                current.append(expression.charAt(i));
            }
            if (priority==1){
                stack.push(expression.charAt(i));
            }
            if (priority>1) {
                current.append(" ");
                while (!stack.empty()) {
                    if (getPriority(stack.peek())>=priority){
                        current.append(stack.pop());
                    }
                    else break;

                }
                stack.push(expression.charAt(i));
            }

            if (priority == -1) {
                current.append(" ");
                while (getPriority(stack.peek())!=1){
                    current.append(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) current.append(stack.pop());

        return current.toString();
    }

    private static String addingNulls(String expr){
        StringBuilder preparedExpression = new StringBuilder();

        for (int i=0; i<expr.length(); i++) {
            char temp = expr.charAt(i);
            if (temp=='-') {
                if (i==0) {
                    preparedExpression.append('0');
                } else if (expr.charAt(i-1) == '(') {
                    preparedExpression.append('0');
                }
            }
            preparedExpression.append(temp);
        }

        return preparedExpression.toString();
    }

    private static double RPNtoResult(String rpn) {
        StringBuilder operand = new StringBuilder();

        Stack <Double> stack = new Stack<>();
        char temp;
        for (int i =0; i<rpn.length(); i++) {
            temp = rpn.charAt(i);
            if (temp == ' ') {
                continue;
            }
            if (getPriority(temp)==0){
                while (temp != ' ' && getPriority(temp)==0) {
                    operand.append(temp);
                    i++;
                    temp = rpn.charAt(i);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand.setLength(0);
            }

            double a, b;
            if (getPriority(temp)>1) {
                a = stack.pop();
                b = stack.pop();
                if (temp == '+') {
                    stack.push(b+a);
                }
                if (temp == '-') {
                    stack.push(b-a);
                }
                if (temp == 'x') {
                    stack.push(b*a);
                }
                if (temp == '/') {
                    stack.push(b/a);
                }
            }
        }
        return stack.pop();
     }

    private static int getPriority(char token) {
        if (token == 'x' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }
}
