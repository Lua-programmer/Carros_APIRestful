package io.luaprogrammer.carros.api.domain.repository;

import io.luaprogrammer.carros.api.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

 Optional<Usuario> findUsuarioByLogin(String login);
}
