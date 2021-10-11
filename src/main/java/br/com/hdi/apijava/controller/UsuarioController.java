package br.com.hdi.apijava.controller;


import br.com.hdi.apijava.dto.UsuarioDTO;
import br.com.hdi.apijava.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Validated
@RestController
@Log4j2
@RequestMapping("api")
public class UsuarioController {


@Autowired
UsuarioService usuarioService;



    @GetMapping("/usuario/{documento}")
    @ApiOperation("Api responsável por buscar um usuário pelo documento")
    public ResponseEntity<UsuarioDTO>buscarUsuarioPorDocumento(@PathVariable("documento") Long documento) {

        return ResponseEntity.ok((usuarioService.buscarUsuarioPorDocumento(documento)));
    }



    @GetMapping("/usuario")
    @ApiOperation("Api responsável por buscar uma lista com todos os usuários cadastrados")
    public ResponseEntity <List<UsuarioDTO>>buscarUsuarioPorDocumento() {

        return ResponseEntity.ok((usuarioService.buscarTodosUsuarios()));
    }
    @Transactional
    @PostMapping("/usuario")
    @ApiOperation("Api responsável por inserir um novo usuário")
    public ResponseEntity<UsuarioDTO>saveUsuario(@RequestBody @ApiParam("Dados de um novo usuário") UsuarioDTO userRequestDTO) {

        return ResponseEntity.ok((usuarioService.saveUsuario(userRequestDTO)));
    }

    @PutMapping("/usuario/{id}")
    @ApiOperation("Api responsável por atualizar um usuário")
    public ResponseEntity<UsuarioDTO>atualizarUsuario(@RequestBody @ApiParam("Dados atualizados do usuário") UsuarioDTO userRequestDTO,
                                                      @PathVariable("id") Long id) {

        return ResponseEntity.ok((usuarioService.atualizarUsuario(userRequestDTO,id)));
    }

    @DeleteMapping("/usuario/{id}")
    @ApiOperation("Api responsável por deletar um usuário")
    public ResponseEntity<UsuarioDTO>deletarUsuario(@PathVariable("id") Long id) {

        return ResponseEntity.ok((usuarioService.deletarUsuario(id)));
    }

}
