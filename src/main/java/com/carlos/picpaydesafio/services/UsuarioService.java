package com.carlos.picpaydesafio.services;
import com.carlos.picpaydesafio.entities.Usuario;
import com.carlos.picpaydesafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UserRepository userRepository;

    public Usuario criarNovoUsuario(Usuario usuario){
        userRepository.save(usuario);
        return usuario;
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



}
