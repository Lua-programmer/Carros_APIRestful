package io.luaprogrammer.carros.api.service;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.domain.repository.CarroRepository;
import io.luaprogrammer.carros.api.exception.ObjectNotFoundException;
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

        return  carroRepository.findAll().stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto getCarroById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carroRepository.findById(id).map(CarroDto::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CarroDto> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    public CarroDto insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
        return CarroDto.create(carroRepository.save(carro));
    }


    public CarroDto update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = carroRepository.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            // Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            carroRepository.save(db);

            return CarroDto.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}
