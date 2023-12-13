package com.vakaliuk.mathhelper.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquationFilterDto {
    private Double rootValue;
    @Positive
    @NotNull
    private Integer rootAmount;
}
