package io.luaprogrammer.carros.api.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.luaprogrammer.carros.CarrosApplication;
import io.luaprogrammer.carros.api.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        //dataHora da expiração do token
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(Keys.hmacShaKeyFor(chaveAssinatura.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser() //vai decodificar o token
                .setSigningKey(chaveAssinatura.getBytes()) //define a chave de assinatura
                .parseClaimsJws(token) //passa o token que vai codificar
                .getBody(); //vai retornar os claims do token
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime localDateTime = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();//converter o objeto do tipo date em DateTime
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String)  obterClaims(token).getSubject();
    }


//    public static void main(String[] args){
//        ConfigurableApplicationContext context = SpringApplication.run(CarrosApplication.class);
//        JwtService service = context.getBean(JwtService.class);
//        Usuario usuario = Usuario.builder().login("mluana").build();
//        String token = service.gerarToken(usuario);
//        System.out.println(token);
//
//        boolean isTokenValido = service.tokenValido(token);
//        System.out.println("O token está válido? " + isTokenValido);
//
//        System.out.println(service.obterLoginUsuario(token));
//    }
}
