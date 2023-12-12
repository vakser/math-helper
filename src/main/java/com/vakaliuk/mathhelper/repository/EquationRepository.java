package com.vakaliuk.mathhelper.repository;

import com.vakaliuk.mathhelper.entity.Equation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquationRepository extends JpaRepository<Equation, Long> {
    Equation findByExpression(String expression);
}
