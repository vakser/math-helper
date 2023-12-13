package com.vakaliuk.mathhelper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "roots")
@Data
@NoArgsConstructor
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //@NotEmpty(message = "Value must not be empty")
    Double value;
    Long equationId;

}
