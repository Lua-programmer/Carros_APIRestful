package io.luaprogrammer.carros.api.domain.repository;

import io.luaprogrammer.carros.api.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
