package com.estoque.sistemaestoque.conexao;

import java.sql.Connection;
import java.sql.Statement;

public class Banco {


    public static void criarTabelas(){

        String fornecedor = """
                CREATE TABLE IF NOT EXISTS fornecedor (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cnpj TEXT,
                    telefone TEXT,
                    email TEXT
                );
                """;


        String produto = """
                CREATE TABLE IF NOT EXISTS produto (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    categoria TEXT,
                    quantidade INTEGER,
                    validade TEXT,
                    id_fornecedor INTEGER,
                    FOREIGN KEY(id_fornecedor) REFERENCES fornecedor(id)
                );
                """;


        String movimentacao = """
                CREATE TABLE IF NOT EXISTS movimentacao (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_produto INTEGER,
                    tipo TEXT NOT NULL,
                    quantidade INTEGER NOT NULL,
                    data TEXT,
                    FOREIGN KEY(id_produto) REFERENCES produto(id)
                );
                """;


        try(Connection conexao = Conexao.conectar();
            Statement stmt = conexao.createStatement()){


            stmt.execute(fornecedor);
            stmt.execute(produto);
            stmt.execute(movimentacao);


            System.out.println("Tabelas criadas com sucesso!");


        }catch(Exception e){

            System.out.println("Erro ao criar tabelas: " + e.getMessage());

        }

    }

}