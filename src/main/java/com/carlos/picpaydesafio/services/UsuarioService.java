package com.carlos.picpaydesafio.services;
import com.carlos.picpaydesafio.entities.Carteira;
import com.carlos.picpaydesafio.entities.TipoUsuario;
import com.carlos.picpaydesafio.entities.Usuario;
import com.carlos.picpaydesafio.exceptions.DadosInvalidosException;
import com.carlos.picpaydesafio.exceptions.TipoInvalidoException;
import com.carlos.picpaydesafio.repositories.CarteiraRepository;
import com.carlos.picpaydesafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Map<String, Object> criarNovoUsuario(Usuario usuario, Double saldoInicial){
        if (!validarUsuario(usuario)){
            throw new DadosInvalidosException("Dados do usuário invalido.");
        }

        Carteira carteira = new Carteira();
        carteira.setUsuario(usuario);
        carteira.setSaldo(saldoInicial);
        userRepository.save(usuario);
        carteiraRepository.save(carteira);

        Map<String, Object> retorno = new HashMap<>();
        retorno.put("Usuario", usuario);
        retorno.put("Id Carteira", carteira.getId());
        retorno.put("Saldo", carteira.getSaldo());

        return retorno;
    }

    public Usuario atualizarUsuario(Usuario usuario, Long id){
        Usuario updateUsuario = userRepository.findUsuarioById(id);
        if(updateUsuario == null){return null;}
        if(!usuario.getNome().isEmpty()) updateUsuario.setNome(usuario.getNome());
        if(!usuario.getCpf_cnpj().isEmpty()) updateUsuario.setCpf_cnpj(usuario.getCpf_cnpj());
        if(!usuario.getTipo_usuario().describeConstable().isEmpty()) updateUsuario.setTipo_usuario(usuario.getTipo_usuario());
        userRepository.save(updateUsuario);
        return updateUsuario;
    }

    public boolean enviarDinhero(Double valor, Long id_pagador, Long id_recebedor){
        Usuario pagador = userRepository.findUsuarioById(id_pagador);
        if (pagador.getTipo_usuario() == TipoUsuario.LOJISTA){
           throw new TipoInvalidoException("O tipo de usuário " +  pagador.getTipo_usuario().toString() + " não é autorizado a fazer essa transação.");
        }
        return false;
    }

    private boolean validarUsuario(Usuario usuario){
        return usuario != null
                && validarString(usuario.getNome())
                && validarString(usuario.getCpf_cnpj())
                && validarString(usuario.getEmail())
                && validarString(usuario.getSenha());
    }

    private boolean validarString(String value) {
        return value != null && !value.isEmpty();
    }
}
