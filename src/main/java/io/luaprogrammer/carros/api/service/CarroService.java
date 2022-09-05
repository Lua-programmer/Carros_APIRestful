package io.luaprogrammer.carros.api.service;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.domain.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarroService {

    private CarroRepository carroRepository;

    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }
}
