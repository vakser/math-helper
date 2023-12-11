package com.vakaliuk.mathhelper.util;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component
public class Checker {
    public boolean areBracketsBalanced(String expr) {
        StringBuilder bracketString = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == ')' || expr.charAt(i) == '(') {
                bracketString.append(expr.charAt(i));
            }
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < bracketString.length(); i++) {
            char x = bracketString.charAt(i);
            if (x == '(') {
                stack.push(x);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (x == ')') {
                stack.pop();
            }
        }
        return (stack.isEmpty());
    }

    public boolean containsConsecutiveArithmeticOperators(String expr) {
        return expr.contains("++") || expr.contains("+*") || expr.contains("+/") || expr.contains("+=") ||
               expr.contains("-+") || expr.contains("-*") || expr.contains("-/") || expr.contains("-=") ||
               expr.contains("*+") || expr.contains("**") || expr.contains("*/") || expr.contains("*=") ||
               expr.contains("/+") || expr.contains("/*") || expr.contains("//") || expr.contains("/=");
    }

    public boolean isEquationRoot(String leftExpression, String rightExpression) {
        DoubleEvaluator evaluator = new DoubleEvaluator();
        double leftSideResult = evaluator.evaluate(leftExpression);
        double rightSideResult = evaluator.evaluate(rightExpression);
        return Math.abs(leftSideResult - rightSideResult) <= 0.000000001;
    }
}
