package com.vakaliuk.mathhelper;

import com.vakaliuk.mathhelper.util.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MathHelperApplicationTests {
    @Autowired
    Checker checker;

    @DisplayName("Correct root")
    @Test
    void rootCorrect() {
        String expression = "3*x+2*x=10";
        double root = 2;
        String leftSideExpression = expression.split("=")[0].replaceAll("x", String.valueOf(root));
        String rightSideExpression = expression.split("=")[1].replaceAll("x", String.valueOf(root));

        Assertions.assertTrue(checker.isEquationRoot(leftSideExpression, rightSideExpression), "value is not a root for this equation");
    }

    @DisplayName("Incorrect root")
    @Test
    void rootIncorrect() {
        String expression = "3*x+2*x=10";
        double root = 2.01;
        String leftSideExpression = expression.split("=")[0].replaceAll("x", String.valueOf(root));
        String rightSideExpression = expression.split("=")[1].replaceAll("x", String.valueOf(root));

        Assertions.assertFalse(checker.isEquationRoot(leftSideExpression, rightSideExpression), "value should be a root for this equation");
    }

    @DisplayName("Expression contains consecutive arithmetic operators")
    @Test
    void consecutiveArithmeticOperators() {
        String expression = "3**x+2*x=10";
        Assertions.assertTrue(checker.containsUnacceptableConsecutiveArithmeticOperators(expression), "expression does not contain unacceptable consecutive arithmetic operators");
    }

    @DisplayName(("Brackets are not balanced"))
    @Test
    void bracketsNotBalanced() {
        String expression = "(5*x + 2))*7=112";
        Assertions.assertFalse(checker.areBracketsBalanced(expression), "brackets are balanced");
    }
}
