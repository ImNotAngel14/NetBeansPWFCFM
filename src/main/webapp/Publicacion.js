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
        //document.getElementById("navbarUsername").innerHTML = data.user.username;
        var formato = "data:image/png;base64,";
        document.getElementById('FP').src = formato + data.photo;
        document.getElementById('FPHeader').src = formato + data.photo;
        document.getElementById('username').innerHTML = data.user.username;
        document.getElementById('usernameHeader').innerHTML = data.user.username;
//document.getElementById('email').innerHTML = data.user.email;
        //document.getElementById('fullname').innerHTML = data.user.firstName + " " + data.user.lastName;
        //document.getElementById('age').innerHTML = calculateAge(data.user.birthdate);
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
