package io.luaprogrammer.carros.api.domain.DTO;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CarroDto {

    private Long id;
    private String nome;
    private String tipo;

    public CarroDto(Carro carro) {
        this.id = carro.getId();
        this.nome = carro.getTipo();
        this.tipo = carro.getTipo();
    }
}
