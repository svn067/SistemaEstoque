package com.estoque.sistemaestoque.model;


public class Produto {

    private int id;
    private String nome;
    private String categoria;
    private int quantidade;
    private String validade;
    private int idFornecedor;


    public Produto() {
    }


    public Produto(String nome, String categoria, int quantidade, String validade, int idFornecedor) {
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.validade = validade;
        this.idFornecedor = idFornecedor;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public String getValidade() {
        return validade;
    }


    public void setValidade(String validade) {
        this.validade = validade;
    }


    public int getIdFornecedor() {
        return idFornecedor;
    }


    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

}