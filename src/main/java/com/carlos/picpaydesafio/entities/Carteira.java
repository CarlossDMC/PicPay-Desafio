package com.carlos.picpaydesafio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira")
    private long id;

    @Column
    private Double saldo = 0.0;
}
