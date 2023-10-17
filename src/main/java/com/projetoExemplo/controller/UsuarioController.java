package com.projetoExemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoExemplo.entities.Usuario;
import com.projetoExemplo.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "API REST DE GERENCIAMENTO DE USUARIOS")
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;
  
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Localiza usuario por ID")
    public ResponseEntity<Usuario> getProductById(@PathVariable Long id) {
    Usuario usuario = usuarioService.buscaUsuarioId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);

        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    @GetMapping("/")
    @Operation(summary = "Apresenta todos os usuarios")
    public ResponseEntity<List<Usuario>> buscaTodosUsuarios() {
        List<Usuario> Usuario = null; usuarioService.buscaTodosUsuarios();
        return ResponseEntity.ok(Usuario);

    }

    @PostMapping("/")
    @Operation(summary = "Cadastra usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
    Usuario criarUsuario = usuarioService.salvaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarUsuario);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um usuario")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario Usuario) {
    Usuario alterarUsuario = usuarioService.alterarUsuario(id, Usuario);
        if (alterarUsuario != null) {
        	
            return ResponseEntity.ok(alterarUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um usuario")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {

        boolean deleted = usuarioService.apagarUsuario(id);
        if (deleted) {

        return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}