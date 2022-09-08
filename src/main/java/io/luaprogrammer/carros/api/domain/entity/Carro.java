package io.luaprogrammer.carros.api.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String urlFoto;

    @Column
    private String urlVideo;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String tipo;
}
