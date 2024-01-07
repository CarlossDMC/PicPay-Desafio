package com.carlos.picpaydesafio.services;
import com.carlos.picpaydesafio.dto.AutorizacaoDTO;
import com.carlos.picpaydesafio.entities.Carteira;
import com.carlos.picpaydesafio.entities.TipoUsuario;
import com.carlos.picpaydesafio.entities.Usuario;
import com.carlos.picpaydesafio.exceptions.*;
import com.carlos.picpaydesafio.repositories.CarteiraRepository;
import com.carlos.picpaydesafio.repositories.UserRepository;
import com.carlos.picpaydesafio.infra.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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

    @Transactional
    public void enviarDinhero(Double valor, Long id_pagador, Long id_recebedor){
        Usuario pagador = userRepository.findUsuarioById(id_pagador);
        Usuario recebedor = userRepository.findUsuarioById(id_recebedor);

        if (pagador == null){throw new UsuarioNaoEncontradoException("Pagador não encontrado.");}
        if (recebedor == null){throw new UsuarioNaoEncontradoException("Recebedor não encontrado.");}
        if (pagador.getTipo_usuario() == TipoUsuario.LOJISTA){throw new TipoInvalidoException("O tipo de usuário " +  pagador.getTipo_usuario().toString() + " não é autorizado a fazer essa transação.");}

        Double saldo = carteiraRepository.findSaldoById(id_pagador);
        if (valor > saldo){throw new SaldoInsuficienteException("Saldo insuficiente para essa operação.");}

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AutorizacaoDTO> response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", AutorizacaoDTO.class);
        String resposta = response.getBody().getMessage();
        System.out.println(resposta);
        if(!resposta.equals("Autorizado")){throw new OperacaoNaoPermitidaException("Operação não permitida!");};
        enviarNotificao();
        carteiraRepository.retirarDinhero(valor, id_pagador);
        carteiraRepository.injetarDinhero(valor, id_recebedor);
    }

    private void enviarNotificao(){
        RestTemplate restTemplate = new RestTemplate();
        String mensagem = "Voce enviou dinhero:";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", mensagem, String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK){
            throw new EnviarNotificaoException("Erro ao enviar notificação.");
        }
    }

    private boolean validarUsuario(Usuario usuario){
        return usuario != null
                && utils.validarString(usuario.getNome())
                && utils.validarString(usuario.getCpf_cnpj())
                && utils.validarString(usuario.getEmail())
                && utils.validarString(usuario.getSenha());
    }


}
