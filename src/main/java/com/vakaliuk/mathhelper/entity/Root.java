package com.vakaliuk.mathhelper.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roots")
@Data
@NoArgsConstructor
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotEmpty(message = "Value must not be empty")
    private Double rootValue;
    private Long equationId;

}
