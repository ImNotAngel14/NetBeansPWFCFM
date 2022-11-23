/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("EditText").value = document.getElementById("TextPubli").innerHTML;

$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "CheckSession"
}).done(function(data, textEstado, jqXHR){
    console.log(data);
    if(data.Response == true)
    {
        const formato = "data:image/png;base64,";
        document.getElementById('FPHeader').src = formato + data.photo;
        document.getElementById('UserName').innerHTML = data.user.username;
        document.getElementById('UserPubli').innerHTML = data.user.username;
    }
    else
    {
        //alert("No hay sesion activa");
        window.location.href = "index.html";
    }
}).fail(function(jqXHR, textEstado){
    alert("la solicitud ha regresado un error: " + textEstado);
});

$(document).ready(function()
{
    $("#profileBtn").click(function()
    {
        window.location.href = "perfil.html";
    });
});

function showComents(){ 
    $(".Comentario").show();
                } 

function editPubli(){ 
        //numCaracteres = document.formul.textito.value.length 
        //	document.formul.numCaracteres.value = numCaracteres//
        document.getElementById("TextPubli").innerHTML = document.getElementById("EditText").value;
        } 