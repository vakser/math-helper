package com.vakaliuk.mathhelper.exception;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.repository.EquationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptHandler {
    private final EquationRepository equationRepository;

    public ExceptHandler(EquationRepository equationRepository) {
        this.equationRepository = equationRepository;
    }

    @ExceptionHandler(RootNotCorrectException.class)
    public String handleRootNotCorrectException(HttpServletRequest request, RootNotCorrectException ex, Model model) {
        model.addAttribute("notCorrect", true);
        model.addAttribute("message", ex.getMessage());
        String[] splitMessage = ex.getMessage().split(" ");
        String expression = splitMessage[splitMessage.length - 1];
        Equation equation = equationRepository.findByExpression(expression);
        model.addAttribute("id", equation.getId());
        return "response-root";
    }
}
