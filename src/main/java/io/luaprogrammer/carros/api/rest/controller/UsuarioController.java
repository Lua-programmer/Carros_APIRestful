package io.luaprogrammer.carros.api.rest.controller;

import io.luaprogrammer.carros.api.config.jwt.JwtService;
import io.luaprogrammer.carros.api.config.security.UserDetailsServiceImpl;
import io.luaprogrammer.carros.api.domain.DTO.CredenciaisDTO;
import io.luaprogrammer.carros.api.domain.DTO.TokenDTO;
import io.luaprogrammer.carros.api.domain.entity.Usuario;
import io.luaprogrammer.carros.api.exception.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UserDetailsServiceImpl usuarioService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired

    private JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        String senhaCrip = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCrip);
        return usuarioService.salvar(usuario);
    }

    //método de autenticação de usuario
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder().login(credenciais.getLogin()).senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (SenhaInvalidaException | UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
