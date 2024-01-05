package com.carlos.picpaydesafio.repositories;

import com.carlos.picpaydesafio.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    @Query(value = "SELECT saldo FROM tb_carteira WHERE id_usuario = :id_usuario", nativeQuery = true)
    public Double findSaldoById(@Param("id_usuario") Long id_usuario);


}
