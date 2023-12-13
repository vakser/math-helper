package com.vakaliuk.mathhelper.controller;

import com.vakaliuk.mathhelper.dto.EquationFilterDto;
import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.service.EquationService;
import com.vakaliuk.mathhelper.util.Checker;
import com.vakaliuk.mathhelper.validator.ExpressionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EquationController {
    private final EquationService equationService;
    private final Checker checker;

    @GetMapping("/createEquation")
    public String addEquationForm(Model model) {
        model.addAttribute("equation", new Equation());
        return "equation-form";
    }

    @GetMapping("/")
    public String showEquations(Model model) {
        List<Equation> equations = equationService.getEquations();
        model.addAttribute("equations", equations);
        model.addAttribute("rootValue", new Root().getRootValue());
        model.addAttribute("rootAmount", new Equation());
        model.addAttribute("filter", new EquationFilterDto());
        return "equations";
    }

    @PostMapping("/saveEquation")
    public String saveEquation(@Valid Equation equation, BindingResult result) {
        new ExpressionValidator(checker).validate(equation, result);
        if (result.hasErrors()) {
            return "equation-form";
        }
        equationService.createEquation(equation);
        return "redirect:/";
    }

}
