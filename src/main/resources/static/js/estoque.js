const API = "http://localhost:8080";



document.addEventListener("DOMContentLoaded", () => {

    carregarEstoque();

});





function carregarEstoque(){


    fetch(API + "/produto/listar")


        .then(res => res.json())


        .then(produtos => {


            let tabela =
                document.getElementById("tabelaEstoque");



            tabela.innerHTML = "";



            produtos.forEach(produto => {



                let situacao;



                if(produto.quantidade === 0){

                    situacao = "Esgotado";

                }

                else if(produto.quantidade <= 10){

                    situacao = "Estoque Baixo";

                }

                else{

                    situacao = "Disponível";

                }





                tabela.innerHTML += `


<tr>

<td>${produto.id}</td>

<td>${produto.nome}</td>

<td>${produto.categoria}</td>

<td>${produto.quantidade}</td>

<td>${produto.validade}</td>

<td>${produto.idFornecedor}</td>

<td>${situacao}</td>


</tr>


`;



            });



        });


}