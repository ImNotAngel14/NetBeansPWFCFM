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