package br.com.hdi.apijava.controller;

import br.com.hdi.apijava.dto.ContatoDTO;
import br.com.hdi.apijava.dto.UsuarioDTO;
import br.com.hdi.apijava.service.ContatoService;
import br.com.hdi.apijava.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api")
public class ContatoController {


    @Autowired
    ContatoService contatoService;


    @GetMapping("/contato/{id}")
    @ApiOperation("Api responsável por buscar os contatos cadastrados por id")
    public ResponseEntity<ContatoDTO> buscarUsuarioPorDocumento(@PathVariable("id") Long id) {

        return ResponseEntity.ok((contatoService.buscarUsuarioPorId(id)));
    }

    @GetMapping("/contato")
    @ApiOperation("Api responsável por buscar uma lista com os contatos cadastrados")
    public ResponseEntity <List<ContatoDTO>>buscarUsuarioPorDocumento() {

        return ResponseEntity.ok((contatoService.buscarTodosContatos()));
    }

    @PostMapping("/contato")
    @ApiOperation("Api responsável por inserir um novo contato")
    public ResponseEntity<ContatoDTO>saveUsuario(@RequestBody @ApiParam("Dados para buscar dados Martins") ContatoDTO  contatoRequestDTO) {

        return ResponseEntity.ok((contatoService.saveContato(contatoRequestDTO)));
    }

    @PutMapping("/contato/{id}")
    @ApiOperation("Api responsável por atualizar um contato")
    public ResponseEntity<ContatoDTO>atualizarUsuario(@RequestBody @ApiParam("Dados de um novo contato") ContatoDTO userRequestDTO,
                                                      @PathVariable("id") Long id) {

        return ResponseEntity.ok((contatoService.atualizarContato(userRequestDTO,id)));
    }

    @DeleteMapping("/contato/{id}")
    @ApiOperation("Api responsável por deletar um contato")
    public ResponseEntity<ContatoDTO>deletarUsuario(@PathVariable("id") Long id) {

        return ResponseEntity.ok((contatoService.deletarContato(id)));
    }

}
