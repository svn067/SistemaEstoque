package com.estoque.sistemaestoque.controller;


import com.estoque.sistemaestoque.dao.FornecedorDAO;
import com.estoque.sistemaestoque.model.Fornecedor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {


    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();



    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Fornecedor fornecedor){


        fornecedorDAO.cadastrar(fornecedor);


        return "Fornecedor cadastrado com sucesso!";

    }



    @GetMapping("/listar")
    public List<Fornecedor> listar(){


        return fornecedorDAO.listar();

    }




    @GetMapping("/buscar/{id}")
    public Fornecedor buscar(@PathVariable int id){


        return fornecedorDAO.buscarPorId(id);

    }





    @DeleteMapping("/excluir/{id}")
    public String excluir(@PathVariable int id){


        boolean excluiu = fornecedorDAO.excluir(id);



        if(excluiu){

            return "Fornecedor excluído com sucesso!";

        }


        return "Não foi possível excluir. Fornecedor possui produtos vinculados.";

    }





    @PutMapping("/editar")
    public String editar(@RequestBody Fornecedor fornecedor){


        fornecedorDAO.editar(fornecedor);


        return "Fornecedor atualizado com sucesso!";

    }


}