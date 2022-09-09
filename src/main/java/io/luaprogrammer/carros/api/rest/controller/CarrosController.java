package io.luaprogrammer.carros.api.rest;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/carros")
public class CarrosController {

    private CarroService carroService;

    @GetMapping
    public ResponseEntity listarCarros() {
        return ResponseEntity.ok(carroService.getAllCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity listarCarroById(@PathVariable("id") Long id) {
        Optional<CarroDto> carro = carroService.getCarroById(id);

        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        //if ternário
//        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();

        //If else
//        if ((carro.isPresent())) {
//            return ResponseEntity.ok(carro.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity listarCarroByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDto> carros = carroService.getCarroByTipo(tipo);

        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Carro carro) {

        CarroDto c = carroService.insert(carro);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {

        carro.setId(id);

        CarroDto c = carroService.update(carro, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        carroService.delete(id);

        return "Carro deletado com sucesso";
    }
}
