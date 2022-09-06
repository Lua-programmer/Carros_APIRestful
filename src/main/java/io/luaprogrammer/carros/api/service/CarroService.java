package io.luaprogrammer.carros.api.service;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.domain.repository.CarroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarroService {

    private CarroRepository carroRepository;

    public List<CarroDto> getAllCarros() {
//        List<Carro> carros = carroRepository.findAll();
//        List<CarroDto> list = carros.stream().map(CarroDto::new).collect(Collectors.toList());
//        return list;

        return  carroRepository.findAll().stream().map(CarroDto::new).collect(Collectors.toList());
    }

    public Optional<CarroDto> getCarroById(Long id) {
        return carroRepository.findById(id).map(CarroDto::new); //quando eu quero converter um tipo optional para outro
    }

    public List<CarroDto> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(CarroDto::new).collect(Collectors.toList());
    }

    public Carro insert(Carro carro) {
        return carroRepository.save(carro);
    }


    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<CarroDto> op = getCarroById(id);
        if (op.isPresent()) {
            CarroDto carDB = op.get();

            //Copia as propriedades
            carDB.setNome(carro.getNome());
            carDB.setTipo(carro.getTipo());
            System.out.println("Carro id " + carDB.getId());

            //Atualiza o carro
            carroRepository.save(carDB);
            return carro;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        if (getCarroById(id).isPresent()) {
            carroRepository.deleteById(id);
        }
    }
}
