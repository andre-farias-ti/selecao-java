package com.indra.selecaoJava.controller;

import com.indra.selecaoJava.model.dto.UsuarioDTO;
import com.indra.selecaoJava.model.entidade.Usuario;
import com.indra.selecaoJava.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/usuario"})
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ApiOperation(value = "Retorna usuário por identificador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada  objeto UsuarioDTO por ID"),
            @ApiResponse(code = 400, message = "Erro não exite usuario"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioId(id));
    }

    @ApiOperation(value = "Deleta usuário por identificador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario foi deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro não exite usuario a ser deletado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @GetMapping(path = {"deletar/{id}"})
    public ResponseEntity<?> delectar(@PathVariable Long id) throws Exception {
            usuarioService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Retorna obejeto usuário salvo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada objeto usuarioDTO que foi registrado"),
            @ApiResponse(code = 400, message = "Erro não foi possivel registras o usuário"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @PostMapping(path = {"/salvar"})
    public ResponseEntity<Usuario> findById(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        return ResponseEntity.ok().body(usuarioService.salvar(usuarioDTO));
    }

    @ApiOperation(value = "Retorna obejeto usuário editado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada objeto usuarioDTO que foi alterado"),
            @ApiResponse(code = 400, message = "Erro não foi possivel editar o usuário"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @PostMapping(path = {"/editar"})
    public ResponseEntity<UsuarioDTO> editar(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        return ResponseEntity.ok().body(usuarioService.editar(usuarioDTO));
    }

    @ApiOperation(value = "Retorna obejeto usuário por filtro, que pode ser nome ou CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornada objeto UsuarioDTO pelos filtros passado na requisição"),
            @ApiResponse(code = 400, message = "Erro não exite usuario ou login e senha erradas"),
            @ApiResponse(code = 500, message = "Erro interno do servidor"),
    })
    @GetMapping(value = "/buscar-por-filtro")
    public ResponseEntity<List<UsuarioDTO>> buscarPorFiltro(@RequestParam(required = false) String nome,
                                                         @RequestParam(required = false) String cpf) throws Exception {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioFiltro(cpf, nome));
    }
}
