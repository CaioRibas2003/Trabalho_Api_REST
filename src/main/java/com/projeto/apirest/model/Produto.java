package com.projeto.apirest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //Dizer que será uma tabela no nosso BD
public class Produto {

    @Id //Id vai ser PK do banco de dados - o Spring faz automaticamente as tabelas
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera e incrementa o ID automaticamente aqui
    private Long id;

    private String nome;
    private String descricao;
    private double preco;

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
