package com.carlos.picpaydesafio.entities;

import jakarta.persistence.*;

@Entity(name = "tb_carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira")
    private long id;

    @Column
    private Double saldo;


    @JoinColumn(name = "id_usuario")
    @OneToOne
    private Usuario usuario;

}
