package io.luaprogrammer.carros.api.config.security;

import io.luaprogrammer.carros.api.domain.entity.Usuario;
import io.luaprogrammer.carros.api.domain.repository.UserRepository;
import io.luaprogrammer.carros.api.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Usuario salvar(Usuario usuario) {
        return userRepository.save(usuario);
    }


    public UserDetails autenticar(Usuario usuario) {
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());//primeiro passa a senha de comparação]
        if (senhasBatem) return userDetails;
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = userRepository.findUsuarioByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }

}
