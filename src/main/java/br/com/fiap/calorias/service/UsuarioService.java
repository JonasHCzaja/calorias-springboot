package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvar(UsuarioCadastroDTO usuarioCadastroDto){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDTO buscarPorId(Long id){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
    }

    public List<UsuarioExibicaoDTO> listarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuario nao encontrado");
        }
    }

    public Usuario atualizar(Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getUsuarioId());

        if (usuarioOptional.isPresent()){
            return usuarioRepository.save(usuario);
            /*                                              --save()--
            *   O metodo "save()" tem a capacidade de decidir se a operacao a ser executada sera de inclusao ou de atualizacao.
            * Isso e possivel por conta do objeto fornecido como parametro conter o identificador unico (usuarioId) ou nao.
            *
            *                                       usuarioId = true -> update
            *                                       usuarioId = false -> insert
            * */
        } else {
            throw new RuntimeException("Usuario nao encontrado");
        }
    }



    public UsuarioExibicaoDTO buscarUsuarioPeloNome(String nome){
        Optional<Usuario> usuarioOptional = usuarioRepository.buscarUsuarioPeloNome(nome);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
    }

//    public UsuarioExibicaoDTO buscarUsuarioPeloEmail(String email){
//        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
//
//        if (usuarioOptional.isPresent()){
//            return new UsuarioExibicaoDTO(usuarioOptional.get());
//        } else {
//            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
//        }
//
//    }

}
