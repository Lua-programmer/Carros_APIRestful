package io.luaprogrammer.carros.api.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredenciaisDTO {
    private String login;
    private String senha;
}
