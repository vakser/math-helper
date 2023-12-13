package com.vakaliuk.mathhelper;

import com.vakaliuk.mathhelper.dto.EquationFilterDto;
import com.vakaliuk.mathhelper.entity.Equation;
import com.vakaliuk.mathhelper.entity.Root;
import com.vakaliuk.mathhelper.repository.EquationRepository;
import com.vakaliuk.mathhelper.repository.RootRepository;
import com.vakaliuk.mathhelper.service.EquationService;
import com.vakaliuk.mathhelper.service.RootService;
import com.vakaliuk.mathhelper.util.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class MathHelperApplicationModuleTests {
    @Autowired
    private EquationRepository equationRepository;
    @Autowired
    private RootRepository rootRepository;
    @Autowired
    private EquationService equationService;
    @Autowired
    private RootService rootService;
    @Autowired
    private Checker checker;

    @Test
    public void createEquationTest() {
        equationService.createEquation(new Equation("2*x+10=20"));
        Equation equation = equationRepository.findByExpression("2*x+10=20");
        assertEquals("2*x+10=20", equation.getExpression(), "find by expression");
    }

    @Test
    public void createRootForEquationTest() {
        Equation equation = equationRepository.findByExpression("2*x+10=20");
        Root root = new Root();
        root.setEquationId(equation.getId());
        root.setRootValue(5.);
        rootService.saveRoot(root);
        List<Root> roots = rootRepository.findByEquationId(equation.getId());
        Root savedRoot = roots.get(0);
        assertEquals(5., savedRoot.getRootValue(), "Should be 5");
    }

    @Test
    public void getEquationsForRootTest() {
        equationService.createEquation(new Equation("5*x+10=25"));
        equationService.createEquation(new Equation("10+9/x=13"));
        Equation equation1 = equationRepository.findByExpression("5*x+10=25");
        Equation equation2 = equationRepository.findByExpression("10+9/x=13");
        Root root1 = new Root();
        root1.setEquationId(equation1.getId());
        root1.setRootValue(3.);
        rootService.saveRoot(root1);
        Root root2 = new Root();
        root2.setEquationId(equation2.getId());
        root2.setRootValue(3.);
        rootService.saveRoot(root2);
        EquationFilterDto filterDto = new EquationFilterDto();
        filterDto.setRootValue(3.);
        List<Equation> equations = equationService.getEquationsFilteredByRootValue(filterDto);
        assertEquals(2, equations.size(), "Should be 2");
    }

    @DisplayName("Correct root")
    @Test
    void rootCorrect() {
        String expression = "3*x+2*x=10";
        double root = 2;
        String leftSideExpression = expression.split("=")[0].replaceAll("x", String.valueOf(root));
        String rightSideExpression = expression.split("=")[1].replaceAll("x", String.valueOf(root));

        Assertions.assertTrue(checker.isEquationRoot(leftSideExpression, rightSideExpression), "value is not a root for this equation");
    }

    @DisplayName("Incorrect root")
    @Test
    void rootIncorrect() {
        String expression = "3*x+2*x=10";
        double root = 2.01;
        String leftSideExpression = expression.split("=")[0].replaceAll("x", String.valueOf(root));
        String rightSideExpression = expression.split("=")[1].replaceAll("x", String.valueOf(root));

        Assertions.assertFalse(checker.isEquationRoot(leftSideExpression, rightSideExpression), "value should be a root for this equation");
    }

    @DisplayName("Expression contains consecutive arithmetic operators")
    @Test
    void consecutiveArithmeticOperators() {
        String expression = "3**x+2*x=10";
        Assertions.assertTrue(checker.containsUnacceptableConsecutiveArithmeticOperators(expression), "expression does not contain unacceptable consecutive arithmetic operators");
    }

    @DisplayName(("Brackets are not balanced"))
    @Test
    void bracketsNotBalanced() {
        String expression = "(5*x + 2))*7=112";
        Assertions.assertFalse(checker.areBracketsBalanced(expression), "brackets are balanced");
    }

}
