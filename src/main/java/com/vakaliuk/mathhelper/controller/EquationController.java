package com.vakaliuk.mathhelper.controller;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.service.EquationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EquationController {
    private final EquationService equationService;

    @GetMapping("/createEquation")
    public String addEquationForm(Model model) {
        model.addAttribute("equation", new Equation());
        return "equation-form";
    }

    @GetMapping("/")
    public String showEquations(Model model) {
        List<Equation> equations = equationService.getEquations();
        model.addAttribute("equations", equations);
        return "equations";
    }

    @PostMapping("/saveEquation")
    public String saveEquation(Equation equation) {
        equationService.saveEquation(equation);
        return "redirect:/";
    }

}
