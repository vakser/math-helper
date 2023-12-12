package com.vakaliuk.mathhelper.validator;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.util.Checker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ExpressionValidator implements Validator {
    private final Checker checker;

    public ExpressionValidator(Checker checker) {
        this.checker = checker;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Equation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Equation equation = (Equation) target;
        if (!checker.areBracketsBalanced(equation.getExpression())) {
            errors.rejectValue("expression", null, "Brackets within expression are not balanced");
        }
        if (checker.containsUnacceptableConsecutiveArithmeticOperators(equation.getExpression())) {
            errors.rejectValue("expression", null, "Expression contains unacceptable consecutive arithmetic operators");
        }
    }
}
