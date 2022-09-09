package io.luaprogrammer.carros.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //habilitar a classe de segurança web
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecutiryConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeHttpRequests() //.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                    .anyRequest()
                    .authenticated().and()//volta para a raiz do objeto
                    .csrf().disable(); //desabilita a proteção csrf
            return http.build();
        }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("userPass"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
