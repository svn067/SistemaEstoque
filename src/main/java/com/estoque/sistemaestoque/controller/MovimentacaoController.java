package com.estoque.sistemaestoque.controller;


import com.estoque.sistemaestoque.dao.MovimentacaoDAO;
import com.estoque.sistemaestoque.model.Movimentacao;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {


    private final MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();



    @PostMapping("/registrar")
    public String registrar(@RequestBody Movimentacao movimentacao){


        boolean sucesso = movimentacaoDAO.registrar(movimentacao);


        if(sucesso){

            return "Movimentação registrada com sucesso!";

        }


        return "Não foi possível registrar a movimentação!";

    }



    @GetMapping("/listar")
    public List<Movimentacao> listar(){


        return movimentacaoDAO.listar();

    }


}