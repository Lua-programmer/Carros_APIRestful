package io.luaprogrammer.carros;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService carroService;

    @Test
    public void testSave() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");
        //inserindo o carro
        CarroDto c = carroService.insert(carro);


        //garante que ele foi inserido
        assertNotNull(c);
        Long id = c.getId();

        //verifica se ele possui um id
        assertNotNull(id);

        //Buscar o Objeto
        Optional<CarroDto> op = carroService.getCarroById(id);
        assertTrue(op.isPresent());

        c = op.get();
        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        //Deletar o objeto
        carroService.delete(id);

        //Verificar se deletou
        assertFalse(carroService.getCarroById(id).isPresent());
    }

    @Test
    public void test2() {

    }

}
