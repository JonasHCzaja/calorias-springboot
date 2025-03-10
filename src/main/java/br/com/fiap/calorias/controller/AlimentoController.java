package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDTO salvar(@RequestBody AlimentoCadastroDTO alimento){
        return alimentoService.salvarAlimento(alimento);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarTodos(){
        return alimentoService.listarTodos();
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorId(@PathVariable Long alimentoId){
        try {
            return ResponseEntity.ok(alimentoService.buscarPorId(alimentoId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alimentoId){
        alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    public ResponseEntity<AlimentoExibicaoDTO> atualizar(@RequestBody AlimentoCadastroDTO alimentoDTO){
        try {
            AlimentoExibicaoDTO alimentoExibicaoDTO = alimentoService.atualizar(alimentoDTO);
            return ResponseEntity.ok(alimentoExibicaoDTO);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/alimentos", params = "nome")
    public ResponseEntity<AlimentoExibicaoDTO> buscarAlimentoPorNome(@RequestParam String nome){
        try {
            return ResponseEntity.ok(alimentoService.buscarAlimentoPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    //api/alimentos?minimo=200&maximo=500
    @GetMapping(value = "/alimentos", params = {"minimo", "maximo"})
    public List<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(
            @RequestParam Double minimo,
            @RequestParam Double maximo
    ){
        return alimentoService.listarAlimentosPorFaixaDeCalorias(minimo, maximo);
    }

    //@RequestMapping-> É uma anotação mais genérica e pode ser usada para mapear diferentes métodos HTTP (GET, POST, PUT, DELETE etc.) para um endpoint.
    //@GetMapping-> É uma anotação especializada e mais concisa
}
