package io.luaprogrammer.carros.api.service;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.domain.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarroService {

    private CarroRepository carroRepository;

    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return carroRepository.findById(id);
    }

    public List<Carro> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }
}
