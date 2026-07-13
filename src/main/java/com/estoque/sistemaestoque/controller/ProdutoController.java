package com.estoque.sistemaestoque.controller;


import com.estoque.sistemaestoque.dao.ProdutoDAO;
import com.estoque.sistemaestoque.model.Produto;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {


    private final ProdutoDAO produtoDAO = new ProdutoDAO();



    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Produto produto){


        produtoDAO.cadastrar(produto);


        return "Produto cadastrado com sucesso!";

    }




    @GetMapping("/listar")
    public List<Produto> listar(){


        return produtoDAO.listar();

    }





    @GetMapping("/buscar/{id}")
    public Produto buscar(@PathVariable int id){


        return produtoDAO.buscarPorId(id);

    }





    @DeleteMapping("/excluir/{id}")
    public String excluir(@PathVariable int id){


        boolean excluiu = produtoDAO.excluir(id);



        if(excluiu){

            return "Produto excluído com sucesso!";

        }


        return "Não foi possível excluir. Produto possui movimentações vinculadas.";

    }





    @PutMapping("/editar")
    public String editar(@RequestBody Produto produto){


        produtoDAO.editar(produto);


        return "Produto atualizado com sucesso!";

    }


}