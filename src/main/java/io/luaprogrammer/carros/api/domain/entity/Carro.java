package io.luaprogrammer.carros.api.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carro {

    private Long id;
    private String nome;
}
