package com.estoque.sistemaestoque.model;


public class Movimentacao {

    private int id;
    private int idProduto;
    private String tipo;
    private int quantidade;
    private String data;


    public Movimentacao() {
    }


    public Movimentacao(int idProduto, String tipo, int quantidade, String data) {
        this.idProduto = idProduto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getIdProduto() {
        return idProduto;
    }


    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }

}