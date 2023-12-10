package com.vakaliuk.mathhelper.service;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.exception.ExpressionNotValidException;
import com.vakaliuk.mathhelper.repository.EquationRepository;
import com.vakaliuk.mathhelper.util.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquationService {
    private final EquationRepository equationRepository;
    private final Validator validator;

    public List<Equation> getEquations() {
        return equationRepository.findAll();
    }

    public void saveEquation(Equation equation) {
        if (!validator.areBracketsBalanced(equation.getExpression()) || validator.containsConsecutiveArithmeticOperators(equation.getExpression())) {
            throw new ExpressionNotValidException("Expression is not valid (either brackets are not balanced or expression " +
                    "contains unacceptable consecutive arithmetic operators)");
        }
        equationRepository.save(equation);
    }

    public Equation getEquationById(Long equationId) {
        return equationRepository.findById(equationId).orElseThrow(() -> new EntityNotFoundException("Equation with id " + equationId + " not found"));
    }
}
