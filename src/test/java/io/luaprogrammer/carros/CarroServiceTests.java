package io.luaprogrammer.carros;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class CarroServiceTests {

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
    public void testLista() {
        List<CarroDto> carros = carroService.getAllCarros();

        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, carroService.getCarroByTipo("classicos").size());
        assertEquals(10, carroService.getCarroByTipo("esportivos").size());
        assertEquals(10, carroService.getCarroByTipo("luxo").size());

        assertEquals(0, carroService.getCarroByTipo("x").size());
    }

    @Test
    public void testGet() {
        Optional<CarroDto> op = carroService.getCarroById(11L);

        assertTrue(op.isPresent());

        CarroDto c = op.get();

        assertEquals("Ferrari FF", c.getNome());
    }

}
