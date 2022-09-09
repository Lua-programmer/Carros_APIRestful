package io.luaprogrammer.carros.api.config.security;

import io.luaprogrammer.carros.api.domain.entity.Usuario;
import io.luaprogrammer.carros.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = userRepository.findUsuarioByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }

}
