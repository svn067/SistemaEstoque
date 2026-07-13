package com.estoque.sistemaestoque.dao;

import com.estoque.sistemaestoque.conexao.Conexao;
import com.estoque.sistemaestoque.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {


    public void cadastrar(Produto produto){


        String sql = """
                INSERT INTO produto
                (nome, categoria, quantidade, validade, id_fornecedor)
                VALUES (?, ?, ?, ?, ?)
                """;


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){


            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getValidade());
            stmt.setInt(5, produto.getIdFornecedor());


            stmt.executeUpdate();


            System.out.println("Produto cadastrado!");


        }catch(SQLException e){

            System.out.println("Erro ao cadastrar produto: " + e.getMessage());

        }

    }



    public List<Produto> listar(){


        List<Produto> lista = new ArrayList<>();


        String sql = "SELECT * FROM produto";


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){


            while(rs.next()){


                Produto produto = new Produto();


                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValidade(rs.getString("validade"));
                produto.setIdFornecedor(rs.getInt("id_fornecedor"));


                lista.add(produto);

            }


        }catch(SQLException e){

            System.out.println("Erro ao listar produtos: " + e.getMessage());

        }


        return lista;

    }



    public Produto buscarPorId(int id){


        String sql = "SELECT * FROM produto WHERE id=?";


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){


            stmt.setInt(1,id);


            ResultSet rs = stmt.executeQuery();


            if(rs.next()){


                Produto produto = new Produto();


                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValidade(rs.getString("validade"));
                produto.setIdFornecedor(rs.getInt("id_fornecedor"));


                return produto;

            }


        }catch(SQLException e){

            System.out.println("Erro ao buscar produto: " + e.getMessage());

        }


        return null;

    }



    public boolean excluir(int id){


        String verificar =
                "SELECT COUNT(*) FROM movimentacao WHERE id_produto=?";



        String sql =
                "DELETE FROM produto WHERE id=?";



        try(Connection conexao = Conexao.conectar()){



            PreparedStatement check =
                    conexao.prepareStatement(verificar);



            check.setInt(1,id);



            ResultSet rs =
                    check.executeQuery();




            if(rs.next() && rs.getInt(1) > 0){


                return false;

            }




            PreparedStatement stmt =
                    conexao.prepareStatement(sql);



            stmt.setInt(1,id);



            stmt.executeUpdate();



            return true;



        }catch(SQLException e){


            System.out.println(
                    "Erro ao excluir produto: "
                            + e.getMessage()
            );


            return false;


        }


    }

    public void editar(Produto produto){


        String sql = """
            UPDATE produto
            SET nome=?, categoria=?, validade=?, id_fornecedor=?
            WHERE id=?
            """;


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){


            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setString(3, produto.getValidade());
            stmt.setInt(4, produto.getIdFornecedor());
            stmt.setInt(5, produto.getId());


            stmt.executeUpdate();


            System.out.println("Produto atualizado!");


        }catch(SQLException e){

            System.out.println("Erro ao editar produto: " + e.getMessage());

        }

    }

}