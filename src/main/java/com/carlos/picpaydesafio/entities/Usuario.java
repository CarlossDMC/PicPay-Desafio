package com.carlos.picpaydesafio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_usuario")
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf_cnpj;

    @Column(unique = true)
    private String email;

    private String senha;

    @JoinColumn(name = "id_carteira")
    @OneToOne(cascade = CascadeType.ALL)
    private Carteira carteira;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;

}


