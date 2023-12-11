package com.vakaliuk.mathhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquationFilterDto {
    private Double rootValue;
    private Integer rootAmount;
}
