

$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "CheckSession"
}).done(function(data, textEstado, jqXHR){
    console.log(data);
    if(data.Response == true)
    {
        //alert("Ya tiene una sesion");
        window.location.href = "perfil.html";
    }
}).fail(function(jqXHR, textStatus){
    alert("la solicitud ha regresado un error: " + textStatus);
    console.log(jqXHR);
});

$("#notValid").hide();

$(document).ready(function()
{

    $("#loginForm").submit(function(event)
    {
        event.preventDefault();
        $.ajax({
            data:$(this).serialize(),
            type:"POST",
            dataType: "json",
            url: "Login"
        }).done(function(data){
            console.log(data);
            if(data.Response)
            {
                $("#notValid").hide();
                window.open('perfil.html', '_self');
            }
            else
            {
                $("#notValid").show();
            }
        }).fail(function()
        {
            
            alert("Hubo un error al conectar al servidor. Revisa tu conexion a internet.");
        });
    });
    
    $("#signUp").click(function()
    {
        window.open('register.html', '_self');
    });
    
    $("#signIn").mouseover(function()
    {
        $(".fa-unlock-keyhole").toggleClass("formatotres");
        //$("button i").toggleClass("formatotres");
    });
    
    $("#signIn").mouseleave(function()
    {
        $(".fa-unlock-keyhole").toggleClass("formatotres");
    });
    
    $("#signUp").mouseover(function()
    {
        $(".fa-user-plus").toggleClass("formatotres");
    });
    
    $("#signUp").mouseleave(function()
    {
        $(".fa-user-plus").toggleClass("formatotres");
    });
});