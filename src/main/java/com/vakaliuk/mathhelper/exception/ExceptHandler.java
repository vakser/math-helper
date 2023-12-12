package com.vakaliuk.mathhelper.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptHandler {

    @ExceptionHandler(RootNotCorrectException.class)
    public String handleRootNotCorrectException(HttpServletRequest request, RootNotCorrectException ex, Model model) {
        model.addAttribute("notCorrect", true);
        model.addAttribute("message", ex.getMessage());
        return "response-root";
    }
}
