package io.luaprogrammer.carros.api.service;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.domain.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Carro insert(Carro carro) {
        return carroRepository.save(carro);
    }


    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> op = getCarroById(id);
        if (op.isPresent()) {
            Carro carDB = op.get();

            //Copia as propriedades
            carDB.setNome(carro.getNome());
            carDB.setTipo(carro.getTipo());
            System.out.println("Carro id " + carDB.getId());

            //Atualiza o carro
            carroRepository.save(carDB);
            return carro;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }
}
