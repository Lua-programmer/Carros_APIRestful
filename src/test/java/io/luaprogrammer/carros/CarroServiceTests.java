package io.luaprogrammer.carros;

import io.luaprogrammer.carros.api.domain.DTO.CarroDto;
import io.luaprogrammer.carros.api.domain.entity.Carro;
import io.luaprogrammer.carros.api.exception.ObjectNotFoundException;
import io.luaprogrammer.carros.api.service.CarroService;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


//@RunWith(SpringRunner.class)
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
        c = carroService.getCarroById(id);
        assertNotNull(c);

        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        //Deletar o objeto
        carroService.delete(id);

        //Verificar se deletou
        try {
            assertNull(carroService.getCarroById(id));
            fail("O carro não foi excluído");
        } catch (ObjectNotFoundException e) {
            //OK
        }
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
        CarroDto c = carroService.getCarroById(11L);

        assertNotNull(c);

        assertEquals("Ferrari FF", c.getNome());
    }

}
