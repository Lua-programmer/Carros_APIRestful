package io.luaprogrammer.carros.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class IndexController {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        return "login = " + login + " senha = " + senha;
    }

    //localhost:8080/login?login=Luana&senha=123  para colocar o primeiro parametro para o "?" e os demais "&"
}
