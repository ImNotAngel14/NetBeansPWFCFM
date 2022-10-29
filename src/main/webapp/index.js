$(document).ready(function()
{
    $("#notValid").hide();
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
});