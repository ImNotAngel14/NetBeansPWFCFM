/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
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
   $("#PublicacionForm").submit(function(event)
    {
        event.preventDefault();
        $.ajax({
            data: new FormData(this),
            type:"POST",
            dataType: "json",
            url: "NewPost",
            cache: false,
            contentType: false,
            processData: false
        }).done(function(data){
            console.log(data);
        if(data.posted)
        {
            window.open('home.html', '_self');
            //window.open('index.html', '_self');
        }
        else
        {
            alert("Ocurrió un error al registrar.");
        }
        }).fail(function()
        {
            alert("Hubo un error al conectar al servidor. Revisa tu conexion a internet.");
        });
    });
});


