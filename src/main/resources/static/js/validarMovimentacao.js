const API = "http://localhost:8080";



document.addEventListener("DOMContentLoaded", () => {

    carregarProdutos();

    listarMovimentacoes();

});




// ===============================
// REGISTRAR MOVIMENTAÇÃO
// ===============================


document
    .getElementById("formMovimentacao")
    .addEventListener("submit", function(event){


        event.preventDefault();



        if(!validarMovimentacao()){

            return;

        }



        let movimentacao = {


            idProduto:
                Number(document.getElementById("produto").value),


            tipo:
            document.getElementById("tipo").value,


            quantidade:
                Number(document.getElementById("quantidade").value),


            data:
            document.getElementById("data").value


        };




        fetch(API + "/movimentacao/registrar",
            {

                method:"POST",

                headers:
                    {
                        "Content-Type":"application/json"
                    },


                body:
                    JSON.stringify(movimentacao)


            })


            .then(res => res.text())


            .then(msg => {


                alert(msg);


                document
                    .getElementById("formMovimentacao")
                    .reset();



                listarMovimentacoes();


            });



    });






// ===============================
// VALIDAÇÃO
// ===============================


function validarMovimentacao(){


    let produto =
        document.getElementById("produto").value;



    let tipo =
        document.getElementById("tipo").value;



    let quantidade =
        document.getElementById("quantidade").value;



    let data =
        document.getElementById("data").value;




    if(produto === ""){

        alert("Selecione um produto.");

        return false;

    }



    if(tipo === ""){

        alert("Selecione o tipo.");

        return false;

    }



    if(quantidade <= 0){

        alert("Quantidade inválida.");

        return false;

    }



    if(data === ""){

        alert("Informe a data.");

        return false;

    }



    return true;


}






// ===============================
// CARREGAR PRODUTOS
// ===============================


function carregarProdutos(){


    fetch(API + "/produto/listar")


        .then(res => res.json())


        .then(produtos => {



            let select =
                document.getElementById("produto");



            produtos.forEach(produto => {



                select.innerHTML += `

<option value="${produto.id}">

${produto.nome}

</option>

`;


            });


        });


}







// ===============================
// LISTAR MOVIMENTAÇÕES
// ===============================


function listarMovimentacoes(){


    fetch(API + "/movimentacao/listar")


        .then(res => res.json())


        .then(movimentacoes => {



            let tabela =
                document.getElementById("tabelaMovimentacoes");



            tabela.innerHTML = "";



            movimentacoes.forEach(mov => {



                tabela.innerHTML += `

<tr>

<td>${mov.id}</td>

<td>${mov.idProduto}</td>

<td>${mov.tipo}</td>

<td>${mov.quantidade}</td>

<td>${mov.data}</td>

</tr>

`;


            });


        });


}