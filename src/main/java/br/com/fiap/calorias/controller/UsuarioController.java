package br.com.fiap.calorias.controller;


import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDto){
        return usuarioService.salvar(usuarioCadastroDto);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarTodos(){

        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        usuarioService.excluir(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizar(usuario);
    }

    @GetMapping("/usuarios/nome/{nome}")
    public UsuarioExibicaoDTO buscarUsuarioPeloNome(@PathVariable String nome){
        return usuarioService.buscarUsuarioPeloNome(nome);
    }


    //api/usuarios?nome=Pedro
    @GetMapping(value = "/usuarios", params = "nome")
    public UsuarioExibicaoDTO buscarUsuarioPorNome(@RequestParam String nome){
        return usuarioService.buscarUsuarioPeloNome(nome);
    }

//    @GetMapping(value = "/usuarios", params = "email")
//    public UsuarioExibicaoDTO buscarUsuarioPeloEmail(@RequestParam String email){
//        return usuarioService.buscarUsuarioPeloEmail(email);
//    }

}
