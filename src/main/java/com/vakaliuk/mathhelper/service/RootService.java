package com.vakaliuk.mathhelper.service;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.exception.RootNotCorrectException;
import com.vakaliuk.mathhelper.repository.EquationRepository;
import com.vakaliuk.mathhelper.repository.RootRepository;
import com.vakaliuk.mathhelper.util.Checker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RootService {
    private final RootRepository rootRepository;
    private final EquationRepository equationRepository;
    private final Checker checker;

    public void saveRoot(Root root) {
        Equation equation = equationRepository.findById(root.getEquationId()).get();
        String leftSideExpression = equation.getExpression().split("=")[0].replaceAll("x", String.valueOf(root.getValue()));
        String rightSideExpression = equation.getExpression().split("=")[1].replaceAll("x", String.valueOf(root.getValue()));
        if (!checker.isEquationRoot(leftSideExpression, rightSideExpression)) {
            throw new RootNotCorrectException("Root " + root.getValue() + " is not correct for equation " + equation.getExpression());
        }
        rootRepository.save(root);
    }

}
