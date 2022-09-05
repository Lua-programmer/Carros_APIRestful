package io.luaprogrammer.carros.api.rest;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/carros")
public class CarrosController {

    private CarroService carroService;

    @GetMapping
    public List<Carro> listarCarros() {
        return carroService.getAllCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> listarCarros(@PathVariable("id") Long id) {
        return carroService.getCarroById(id);
    }
}
