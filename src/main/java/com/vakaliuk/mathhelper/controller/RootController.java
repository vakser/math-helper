package com.vakaliuk.mathhelper.controller;

import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.service.EquationService;
import com.vakaliuk.mathhelper.service.RootService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.ScriptException;

@Controller
@RequiredArgsConstructor
public class RootController {
    private final RootService rootService;
    private final EquationService equationService;

    @PostMapping("/saveRoot")
    public String saveRoot(Root root, Model model) {
        rootService.saveRoot(root);
        model.addAttribute("root", root);
        return "redirect:/";
    }

    @GetMapping("/addRoot")
    public String addRootForm(Model model, @RequestParam Long id) {
        Equation equation = equationService.getEquationById(id);
        model.addAttribute("equation", equation);
        model.addAttribute("id", id);
        Root root = new Root();
        root.setEquationId(id);
        model.addAttribute("root", root);
        return "root-form";
    }
}
