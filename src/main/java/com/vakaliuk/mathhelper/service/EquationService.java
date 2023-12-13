package com.vakaliuk.mathhelper.service;

import com.vakaliuk.mathhelper.dto.EquationFilterDto;
import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.exception.ExpressionNotValidException;
import com.vakaliuk.mathhelper.repository.EquationRepository;
import com.vakaliuk.mathhelper.repository.RootRepository;
import com.vakaliuk.mathhelper.util.Checker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquationService {
    private final EquationRepository equationRepository;
    private final RootRepository rootRepository;
    private final Checker checker;

    public List<Equation> getEquations() {
        return equationRepository.findAll();
    }

    public void createEquation(Equation equation) {
        if (!checker.areBracketsBalanced(equation.getExpression()) || checker.containsUnacceptableConsecutiveArithmeticOperators(equation.getExpression())) {
            throw new ExpressionNotValidException("Expression is not valid (either brackets are not balanced or expression " +
                    "contains unacceptable consecutive arithmetic operators)");
        }
        equationRepository.save(equation);
    }

    public Equation getEquationById(Long equationId) {
        return equationRepository.findById(equationId).orElseThrow(() -> new EntityNotFoundException("Equation with id " + equationId + " not found"));
    }

    public List<Equation> getEquationsFilteredByRootValue(EquationFilterDto equationFilterDto) {
        Double rootValue = equationFilterDto.getRootValue();
        List<Root> roots = rootRepository.findByRootValue(rootValue);
        List<Equation> equations = new ArrayList<>();
        for (Root root : roots) {
            equations.add(equationRepository.findById(root.getEquationId()).get());
        }
        return equations;
    }

    public List<Equation> getEquationsFilteredByRootAmount(EquationFilterDto equationFilterDto) {
        Integer rootAmount = equationFilterDto.getRootAmount();
        List<Equation> equations = equationRepository.findAll();
        List<Equation> equationsFilteredByRootAmount = new ArrayList<>();
        for (Equation equation : equations) {
            List<Root> roots = rootRepository.findByEquationId(equation.getId());
            if (roots.size() == rootAmount) {
                equationsFilteredByRootAmount.add(equation);
            }
        }
        return equationsFilteredByRootAmount;
    }
}
