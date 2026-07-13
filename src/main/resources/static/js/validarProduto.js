const API = "http://localhost:8080";



document.addEventListener("DOMContentLoaded", () => {


    carregarFornecedores();

    listarProdutos();



});





// ===============================
// CADASTRAR PRODUTO
// ===============================


document
    .getElementById("formProduto")
    .addEventListener("submit", function(event){


        event.preventDefault();



        if(!validarProduto()){

            return;

        }



        let produto = {


            nome:
            document.getElementById("nome").value,


            categoria:
            document.getElementById("categoria").value,


            quantidade:
                Number(document.getElementById("quantidade").value),


            validade:
            document.getElementById("validade").value,


            idFornecedor:
                Number(document.getElementById("fornecedor").value)



        };





        let url;
        let metodo;



        if(window.produtoEditando){


            produto.id = window.produtoEditando;


            url = API + "/produto/editar";

            metodo = "PUT";



        }else{


            url = API + "/produto/cadastrar";

            metodo = "POST";


        }




        fetch(url,
            {

                method:metodo,

                headers:
                    {
                        "Content-Type":"application/json"
                    },


                body:
                    JSON.stringify(produto)


            })


            .then(res => res.text())


            .then(msg => {


                alert(msg);



                document
                    .getElementById("formProduto")
                    .reset();



                window.produtoEditando = null;



                document.querySelector(
                    "#formProduto button"
                ).innerText =
                    "Cadastrar Produto";



                listarProdutos();



            });


    });






// ===============================
// VALIDAÇÃO
// ===============================


function validarProduto(){


    let nome =
        document.getElementById("nome").value.trim();



    let categoria =
        document.getElementById("categoria").value.trim();



    let quantidade =
        document.getElementById("quantidade").value;



    let fornecedor =
        document.getElementById("fornecedor").value;



    if(nome === ""){

        alert("Informe o nome do produto.");

        return false;

    }



    if(categoria === ""){

        alert("Informe a categoria.");

        return false;

    }



    if(quantidade <= 0){

        alert("Quantidade inválida.");

        return false;

    }



    if(fornecedor === ""){

        alert("Selecione um fornecedor.");

        return false;

    }



    return true;


}






// ===============================
// LISTAR PRODUTOS
// ===============================


function listarProdutos(){


    fetch(API + "/produto/listar")


        .then(res => res.json())


        .then(produtos => {


            let tabela =
                document.getElementById("tabelaProdutos");



            tabela.innerHTML = "";



            produtos.forEach(produto => {



                tabela.innerHTML += `


<tr>

<td>${produto.id}</td>

<td>${produto.nome}</td>

<td>${produto.categoria}</td>

<td>${produto.quantidade}</td>

<td>${produto.validade}</td>

<td>${produto.idFornecedor}</td>


<td>

<button onclick="editarProduto(${produto.id})">
Editar
</button>


<button onclick="excluirProduto(${produto.id})">
Excluir
</button>


</td>


</tr>


`;



            });



        });



}







// ===============================
// CARREGAR FORNECEDORES NO SELECT
// ===============================


function carregarFornecedores(){


    fetch(API + "/fornecedor/listar")


        .then(res => res.json())


        .then(fornecedores => {



            let select =
                document.getElementById("fornecedor");



            fornecedores.forEach(fornecedor => {



                select.innerHTML += `

<option value="${fornecedor.id}">

${fornecedor.nome}

</option>

`;


            });



        });



}

function excluirProduto(id){


    if(!confirm("Deseja realmente excluir este produto?")){

        return;

    }



    fetch(API + "/produto/excluir/" + id,
        {

            method:"DELETE"

        })


        .then(res => res.text())


        .then(msg => {


            alert(msg);


            listarProdutos();


        });



}






function editarProduto(id){


    fetch(API + "/produto/buscar/" + id)


        .then(res => res.json())


        .then(produto => {



            document.getElementById("nome").value =
                produto.nome;



            document.getElementById("categoria").value =
                produto.categoria;



            document.getElementById("quantidade").value =
                produto.quantidade;



            document.getElementById("validade").value =
                produto.validade;



            document.getElementById("fornecedor").value =
                produto.idFornecedor;




            window.produtoEditando =
                produto.id;




            document.querySelector(
                "#formProduto button"
            ).innerText =
                "Salvar Alterações";



        });


}