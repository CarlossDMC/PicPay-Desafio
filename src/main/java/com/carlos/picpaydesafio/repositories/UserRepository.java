package com.carlos.picpaydesafio.repositories;

import com.carlos.picpaydesafio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    public Usuario findUsuarioById(Long id_usuario);
}
