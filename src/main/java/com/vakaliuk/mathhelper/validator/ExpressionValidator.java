package com.vakaliuk.mathhelper.validator;

import com.vakaliuk.mathhelper.entity.Equation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ExpressionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Equation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Equation equation = (Equation) target;
        if (equation.getExpression().isEmpty()) {
            errors.rejectValue("expression", null, "Expression should not be empty");
        }
    }
}
