package io.luaprogrammer.carros.api.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String login;
    private String token;
}
