package com.estoque.sistemaestoque.dao;

import com.estoque.sistemaestoque.conexao.Conexao;
import com.estoque.sistemaestoque.model.Movimentacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MovimentacaoDAO {


    public boolean registrar(Movimentacao movimentacao){


        String buscarEstoque =
                "SELECT quantidade FROM produto WHERE id=?";


        String atualizarEstoque =
                "UPDATE produto SET quantidade=? WHERE id=?";


        String inserirMovimentacao =
                """
                INSERT INTO movimentacao
                (id_produto, tipo, quantidade, data)
                VALUES (?, ?, ?, ?)
                """;


        try(Connection conexao = Conexao.conectar()){


            PreparedStatement busca =
                    conexao.prepareStatement(buscarEstoque);


            busca.setInt(1, movimentacao.getIdProduto());


            ResultSet rs = busca.executeQuery();


            if(!rs.next()){

                System.out.println("Produto não encontrado!");
                return false;

            }


            int estoqueAtual = rs.getInt("quantidade");


            int novoEstoque;


            if(movimentacao.getTipo().equalsIgnoreCase("entrada")){


                novoEstoque = estoqueAtual + movimentacao.getQuantidade();


            }else{


                novoEstoque = estoqueAtual - movimentacao.getQuantidade();


                if(novoEstoque < 0){

                    System.out.println("Estoque insuficiente!");
                    return false;

                }

            }



            PreparedStatement atualizar =
                    conexao.prepareStatement(atualizarEstoque);


            atualizar.setInt(1, novoEstoque);
            atualizar.setInt(2, movimentacao.getIdProduto());

            atualizar.executeUpdate();



            PreparedStatement inserir =
                    conexao.prepareStatement(inserirMovimentacao);


            inserir.setInt(1, movimentacao.getIdProduto());
            inserir.setString(2, movimentacao.getTipo());
            inserir.setInt(3, movimentacao.getQuantidade());
            inserir.setString(4, movimentacao.getData());


            inserir.executeUpdate();


            System.out.println("Movimentação registrada!");


            return true;



        }catch(SQLException e){

            System.out.println("Erro na movimentação: " + e.getMessage());

            return false;

        }

    }



    public List<Movimentacao> listar(){


        List<Movimentacao> lista = new ArrayList<>();


        String sql = "SELECT * FROM movimentacao";


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){


            while(rs.next()){


                Movimentacao movimentacao =
                        new Movimentacao();


                movimentacao.setId(rs.getInt("id"));
                movimentacao.setIdProduto(rs.getInt("id_produto"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setQuantidade(rs.getInt("quantidade"));
                movimentacao.setData(rs.getString("data"));


                lista.add(movimentacao);

            }


        }catch(SQLException e){

            System.out.println("Erro ao listar movimentações: "
                    + e.getMessage());

        }


        return lista;

    }

}