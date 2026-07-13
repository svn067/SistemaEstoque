package com.estoque.sistemaestoque;

import com.estoque.sistemaestoque.conexao.Banco;
import com.estoque.sistemaestoque.conexao.Conexao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SistemaEstoqueApplication {


    public static void main(String[] args) {

        SpringApplication.run(SistemaEstoqueApplication.class, args);


        Conexao.conectar();

        Banco.criarTabelas();

    }

}