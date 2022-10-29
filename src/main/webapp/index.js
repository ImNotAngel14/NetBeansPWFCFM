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
            window.open('home.html', '_self');
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