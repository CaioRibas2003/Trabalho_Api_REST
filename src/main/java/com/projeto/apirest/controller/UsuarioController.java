package com.projeto.apirest.controller;

import com.projeto.apirest.model.Usuario;
import com.projeto.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    //Para testes e apresentaçãao:
    //no postman selecionar o tipo da requisição, json
    //já o link é localhost, porta padrao do Springboot e /usuarios

    private final UsuarioService service;

    //Construtor e injeção de dependencia
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //tipo post, para criar os usuarios, também retorna o codigo http de sucesso(201 Created) e o id gerado
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Usuario usuario) {
        Usuario novo = service.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso! ID: " + novo.getId());
    }

    //Faz a busca de todos os Usuarios, retornando ok e a lista
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    //Faz a busca por id especifico, se não existir retorna um not found, se exisir retorna o Usuario e ok
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable long id) {
        Usuario usuario = service.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        return ResponseEntity.ok(usuario);
    }

    //Atualiza o usuario, se nao existir retorna um not found, se existir retorna ok atualizando ele
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable long id, @RequestBody Usuario dados) {
        Usuario atualizado = service.atualizarUsuario(id, dados);

        if (atualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para atualização.");
        }
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    //Deleta usuario via id, se nao encontrar retorna not found, se encontrar deleta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        boolean removido = service.deletarUsuario(id);

        if (!removido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para exclusão.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
