package com.estoque.sistemaestoque.dao;

import com.estoque.sistemaestoque.conexao.Conexao;
import com.estoque.sistemaestoque.model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FornecedorDAO {


    public void cadastrar(Fornecedor fornecedor){


        String sql = """
                INSERT INTO fornecedor
                (nome, cnpj, telefone, email)
                VALUES (?, ?, ?, ?)
                """;


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){


            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());


            stmt.executeUpdate();


            System.out.println("Fornecedor cadastrado!");


        }catch(SQLException e){

            System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());

        }

    }



    public List<Fornecedor> listar(){


        List<Fornecedor> lista = new ArrayList<>();


        String sql = "SELECT * FROM fornecedor";


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){


            while(rs.next()){


                Fornecedor fornecedor = new Fornecedor();


                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));


                lista.add(fornecedor);

            }


        }catch(SQLException e){

            System.out.println("Erro ao listar fornecedores: " + e.getMessage());

        }


        return lista;

    }



    public boolean excluir(int id){


        String verificar =
                "SELECT COUNT(*) FROM produto WHERE id_fornecedor=?";



        String sql =
                "DELETE FROM fornecedor WHERE id=?";



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
                    "Erro ao excluir fornecedor: "
                            + e.getMessage()
            );


            return false;

        }


    }

    public void editar(Fornecedor fornecedor){


        String sql = """
            UPDATE fornecedor
            SET nome=?, cnpj=?, telefone=?, email=?
            WHERE id=?
            """;


        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){


            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setInt(5, fornecedor.getId());


            stmt.executeUpdate();


            System.out.println("Fornecedor atualizado!");


        }catch(SQLException e){

            System.out.println("Erro ao editar fornecedor: " + e.getMessage());

        }

    }

    public Fornecedor buscarPorId(int id){


        String sql =
                "SELECT * FROM fornecedor WHERE id=?";



        try(Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)){



            stmt.setInt(1,id);



            ResultSet rs = stmt.executeQuery();



            if(rs.next()){


                Fornecedor fornecedor = new Fornecedor();



                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));



                return fornecedor;


            }



        }catch(SQLException e){


            System.out.println(
                    "Erro ao buscar fornecedor: "
                            + e.getMessage()
            );

        }



        return null;

    }

}