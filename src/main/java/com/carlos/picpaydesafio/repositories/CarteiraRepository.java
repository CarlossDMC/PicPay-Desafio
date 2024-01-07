package com.carlos.picpaydesafio.repositories;

import com.carlos.picpaydesafio.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    @Query(value =  "select saldo from tb_carteira c " +
                    "left join tb_usuario u ON (c.id_carteira = u.id_carteira) " +
                    "where id_usuario = :id_usuario", nativeQuery = true)
    public Double findSaldoById(@Param("id_usuario") Long id_usuario);

    @Modifying
    @Query(value = "UPDATE tb_carteira c, tb_usuario u SET c.saldo = c.saldo - :valor WHERE c.id_carteira = u.id_carteira AND u.id_usuario = :id_usuario", nativeQuery = true)
    public void retirarDinhero(@Param("valor") Double valor, @Param("id_usuario") Long id_usuario);

    @Modifying
    @Query(value = "UPDATE tb_carteira c, tb_usuario u SET c.saldo = c.saldo + :valor WHERE c.id_carteira = u.id_carteira AND u.id_usuario = :id_usuario", nativeQuery = true)
    public void injetarDinhero(@Param("valor") Double valor, @Param("id_usuario") Long id_usuario);


}
