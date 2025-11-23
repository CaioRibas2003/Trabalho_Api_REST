package com.projeto.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.apirest.model.Produto;

//Metodos para CRUD e referenciando a tabela Produto
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    }
