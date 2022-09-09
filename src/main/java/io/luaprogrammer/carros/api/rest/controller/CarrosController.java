package io.luaprogrammer.carros.api.rest.controller;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/carros")
public class CarrosController {

    private CarroService carroService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity listarCarros() {
        return ResponseEntity.ok(carroService.getAllCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity listarCarroById(@PathVariable("id") Long id) {
        CarroDto carro = carroService.getCarroById(id);

        return ResponseEntity.ok(carro);

    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity listarCarroByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDto> carros = carroService.getCarroByTipo(tipo);

        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {

        carro.setId(id);

        CarroDto c = carroService.update(carro, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        carroService.delete(id);
    }
}
