const API = "http://localhost:8080";


document.addEventListener("DOMContentLoaded", () => {

    listarFornecedores();

});




// ===============================
// CADASTRAR / EDITAR FORNECEDOR
// ===============================


document
    .getElementById("formFornecedor")
    .addEventListener("submit", function(event){


        event.preventDefault();



        if(!validarFornecedor()){

            return;

        }



        let fornecedor = {


            nome:
            document.getElementById("nome").value,


            cnpj:
            document.getElementById("cnpj").value,


            telefone:
            document.getElementById("telefone").value,


            email:
            document.getElementById("email").value


        };



        let url;

        let metodo;




        if(window.fornecedorEditando){


            fornecedor.id =
                window.fornecedorEditando;



            url =
                API + "/fornecedor/editar";


            metodo =
                "PUT";



        }else{


            url =
                API + "/fornecedor/cadastrar";


            metodo =
                "POST";


        }





        fetch(url,
            {


                method:metodo,


                headers:
                    {

                        "Content-Type":"application/json"

                    },


                body:
                    JSON.stringify(fornecedor)



            })



            .then(res => res.text())



            .then(msg => {



                alert(msg);



                document
                    .getElementById("formFornecedor")
                    .reset();




                window.fornecedorEditando = null;



                document.querySelector(
                    "#formFornecedor button"
                ).innerText =
                    "Cadastrar Fornecedor";




                listarFornecedores();



            });



    });









// ===============================
// VALIDAÇÃO
// ===============================


function validarFornecedor(){



    let nome =
        document.getElementById("nome").value.trim();



    let cnpj =
        document.getElementById("cnpj").value.trim();



    let telefone =
        document.getElementById("telefone").value.trim();



    let email =
        document.getElementById("email").value.trim();






    if(nome === ""){


        alert("Informe o nome da empresa.");


        return false;


    }





    if(cnpj === ""){


        alert("Informe o CNPJ.");


        return false;


    }





    if(telefone === ""){


        alert("Informe o telefone.");


        return false;


    }





    if(email === ""){


        alert("Informe o e-mail.");


        return false;


    }





    return true;


}









// ===============================
// LISTAR FORNECEDORES
// ===============================


function listarFornecedores(){



    fetch(API + "/fornecedor/listar")



        .then(res => res.json())



        .then(fornecedores => {



            let tabela =
                document.getElementById("tabelaFornecedores");



            tabela.innerHTML = "";




            fornecedores.forEach(fornecedor => {



                tabela.innerHTML += `


<tr>


<td>${fornecedor.id}</td>


<td>${fornecedor.nome}</td>


<td>${fornecedor.cnpj}</td>


<td>${fornecedor.telefone}</td>


<td>${fornecedor.email}</td>



<td>


<button onclick="editarFornecedor(${fornecedor.id})">

Editar

</button>




<button onclick="excluirFornecedor(${fornecedor.id})">

Excluir

</button>


</td>



</tr>



`;



            });



        });



}









// ===============================
// EDITAR FORNECEDOR
// ===============================


function editarFornecedor(id){



    fetch(API + "/fornecedor/buscar/" + id)



        .then(res => res.json())



        .then(fornecedor => {




            document.getElementById("nome").value =
                fornecedor.nome;




            document.getElementById("cnpj").value =
                fornecedor.cnpj;




            document.getElementById("telefone").value =
                fornecedor.telefone;




            document.getElementById("email").value =
                fornecedor.email;





            window.fornecedorEditando =
                fornecedor.id;




            document.querySelector(
                "#formFornecedor button"
            ).innerText =
                "Salvar Alterações";



        });



}









// ===============================
// EXCLUIR FORNECEDOR
// ===============================


function excluirFornecedor(id){



    if(!confirm("Deseja realmente excluir este fornecedor?")){


        return;


    }






    fetch(API + "/fornecedor/excluir/" + id,
        {



            method:"DELETE"



        })





        .then(res => res.text())




        .then(msg => {



            alert(msg);



            listarFornecedores();



        });



}