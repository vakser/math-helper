package com.vakaliuk.mathhelper.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptHandler {
    @ExceptionHandler(ExpressionNotValidException.class)
    public String handleExpressionNotValidException(HttpServletRequest request, ExpressionNotValidException ex, Model model) {
        model.addAttribute("notValid", true);
        model.addAttribute("message", ex.getMessage());
        return "response-expression";
    }

    @ExceptionHandler(RootNotCorrectException.class)
    public String handleRootNotCorrectException(HttpServletRequest request, RootNotCorrectException ex, Model model) {
        model.addAttribute("notCorrect", true);
        model.addAttribute("message", ex.getMessage());
        return "response-root";
    }
}
