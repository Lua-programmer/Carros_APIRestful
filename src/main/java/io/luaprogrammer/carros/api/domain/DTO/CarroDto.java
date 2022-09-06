package io.luaprogrammer.carros.api.domain.DTO;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
public class CarroDto {

    private Long id;
    private String nome;
    private String tipo;

    public static CarroDto create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDto.class);
    }
}
