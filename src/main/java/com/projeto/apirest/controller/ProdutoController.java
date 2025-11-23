package com.projeto.apirest.controller;

import com.projeto.apirest.model.Produto;
import com.projeto.apirest.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Para testes e apresentaçãao:
    //no postman selecionar o tipo da requisição, json
    //já o link é localhost, porta padrao do Springboot e /produtos

    private final ProdutoService service;

    //Construtor e injeção de dependencia
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    //tipo post, para criar os produtos, também retorna o codigo http de sucesso(201 Created) e o id gerado
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Produto produto) {
        Produto novo = service.criarProduto(produto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Produto criado com sucesso! ID: " + novo.getId());
    }

    //Faz a busca de todos os Usuarios, retornando ok e a lista
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarProdutos());
    }

    //Faz a busca por id especifico, se não existir retorna um not found, se exisir retorna o Usuario e ok
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable long id) {
        Produto produto = service.buscarPorId(id);

        if (produto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produto);
    }

    //Atualiza o usuario, se nao existir retorna um not found, se existir retorna ok atualizando ele
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable long id, @RequestBody Produto dados) {
        Produto atualizado = service.atualizarProduto(id, dados);

        if (atualizado == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado para atualização.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Produto atualizado com sucesso!");
    }

    //Atualiza o usuario, se nao existir retorna um not found, se existir retorna ok atualizando ele
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        boolean removido = service.deletarProduto(id);

        if (!removido) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado para exclusão.");
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
