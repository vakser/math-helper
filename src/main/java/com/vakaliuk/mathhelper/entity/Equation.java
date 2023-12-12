package com.vakaliuk.mathhelper.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equations")
@Data
@NoArgsConstructor
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String expression;

    public Equation(String expression) {
        this.expression = expression;
    }

}
