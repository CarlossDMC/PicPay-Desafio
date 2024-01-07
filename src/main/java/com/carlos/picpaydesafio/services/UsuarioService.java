package com.carlos.picpaydesafio.services;
import com.carlos.picpaydesafio.entities.Carteira;
import com.carlos.picpaydesafio.entities.TipoUsuario;
import com.carlos.picpaydesafio.entities.Usuario;
import com.carlos.picpaydesafio.exceptions.DadosInvalidosException;
import com.carlos.picpaydesafio.exceptions.TipoInvalidoException;
import com.carlos.picpaydesafio.exceptions.UsuarioNaoEncontradoException;
import com.carlos.picpaydesafio.repositories.CarteiraRepository;
import com.carlos.picpaydesafio.repositories.UserRepository;
import com.carlos.picpaydesafio.infra.Utils;
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
    private Utils utils;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Map<String, Object> criarNovoUsuario(Usuario usuario){
        if (!validarUsuario(usuario)){
            throw new DadosInvalidosException("Dados do usuário invalido.");
        }
        carteiraRepository.save(usuario.getCarteira());
        userRepository.save(usuario);


        Map<String, Object> retorno = new HashMap<>();
        retorno.put("Usuario", usuario);
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
        Usuario recebedor = userRepository.findUsuarioById(id_recebedor);
        if (pagador == null){
            throw new UsuarioNaoEncontradoException("Usuario com o id " + pagador.getId() + " não encontrado.");
        }
        if (recebedor == null){
            throw new UsuarioNaoEncontradoException("Usuario com o id " + recebedor.getId() + " não encontrado.");
        }
        if (pagador.getTipo_usuario() == TipoUsuario.LOJISTA){
           throw new TipoInvalidoException("O tipo de usuário " +  pagador.getTipo_usuario().toString() + " não é autorizado a fazer essa transação.");
        }



        return true;
    }

    private boolean validarUsuario(Usuario usuario){
        return usuario != null
                && utils.validarString(usuario.getNome())
                && utils.validarString(usuario.getCpf_cnpj())
                && utils.validarString(usuario.getEmail())
                && utils.validarString(usuario.getSenha());
    }


}
