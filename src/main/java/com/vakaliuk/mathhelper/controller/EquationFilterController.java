package com.vakaliuk.mathhelper.controller;

import com.vakaliuk.mathhelper.dto.EquationFilterDto;
import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.service.EquationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EquationFilterController {
    private final EquationService equationService;

    @GetMapping("/selectByRootValue")
    public String selectEquationsByRootValue(@ModelAttribute("rootValue") double rootValue, Model model) {
        List<Equation> equations = equationService.getEquationsByRootValue(rootValue);
        model.addAttribute("equations", equations);
        return "equations";
    }

    @GetMapping("/findByRootValue")
    public String findByRootForm(Model model, @ModelAttribute("rootValue") double rootValue) {
        Root root = new Root();
        root.setValue(rootValue);
        model.addAttribute("root", root);
        return "equations-by-root-value";
    }

    @GetMapping("/filterEquationsByRootValue")
    public String filterEquationsByRootValue(@ModelAttribute("filter") EquationFilterDto equationFilterDto, Model model) {
        List<Equation> equations = equationService.getEquationsFilteredByRootValue(equationFilterDto);
        model.addAttribute("equations", equations);
        return "equations";
    }

    @GetMapping("/filterEquationsByRootAmount")
    public String filterEquationsByRootAmount(@ModelAttribute("filter") EquationFilterDto equationFilterDto, Model model) {
        List<Equation> equations = equationService.getEquationsFilteredByRootAmount(equationFilterDto);
        model.addAttribute("equations", equations);
        return "equations";
    }

}
