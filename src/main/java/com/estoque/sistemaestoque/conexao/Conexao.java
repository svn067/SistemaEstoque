package com.estoque.sistemaestoque.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:sqlite:estoque.db";


    public static Connection conectar(){

        Connection conexao = null;

        try {

            Class.forName("org.sqlite.JDBC");

            conexao = DriverManager.getConnection(URL);

            System.out.println("Banco conectado com sucesso!");

        } catch (ClassNotFoundException | SQLException e){

            System.out.println("Erro na conexão: " + e.getMessage());

        }

        return conexao;

    }

}