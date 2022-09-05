package io.luaprogrammer.carros.api.rest;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Optional<Carro> listarCarroById(@PathVariable("id") Long id) {
        return carroService.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Carro> listarCarroByTipo(@PathVariable("tipo") String tipo) {
        return carroService.getCarroByTipo(tipo);
    }

    @PostMapping
    public void post(@RequestBody Carro carro) {
        carroService.save(carro);
    }
}
