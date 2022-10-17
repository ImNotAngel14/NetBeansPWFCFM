var email = true;
$(document).ready(function()
{
    loadBirthdateInput();
   $("#registerForm").submit(function(event)
    {
        event.preventDefault();
        if(isValidRegister())
        {
            $.ajax({
                data:$(this).serialize(),
                type:"POST",
                dataType: "json",
                url: "Register"
            }).done(function(data){
                console.log(data);
            if(data.registered)
            {
                alert("registrado correctamente");
                window.open('index.html', '_self');
            }
            else
            {
                alert("no se pudo registrar");
            }
            }).fail(function()
            {
                alert("Hubo un error al conectar al servidor. Revisa tu conexion a internet.");
            });
        }
    });
});

function getToday()
{
    let temp = new Date();
    let year = temp.getFullYear().toString();
    let tMonth = temp.getMonth()+1;
    let month = tMonth.toString();
    let day = temp.getDate();
    var today = year+"-"+month+"-"+day;
    return today;
}

function loadBirthdateInput()
{
    let today = getToday();
    document.getElementById("birthdate").setAttribute("value", today);
    document.getElementById("birthdate").setAttribute("max", today);
}
/////////////////////////////////////////////////
//Validacion de los input
/////////////////////////////////////////////////
function isValidRegister()
{
    if(email === false)
    {
        alert("El email no tiene un formato correcto");
    }
    var pass1 = $("#password").val();
    var pass2 = $("#confirmPassword").val();
    if(pass1 !== pass2)
    {
        alert("Las contraseñas no coinciden");
        return false;
    }
    return true;
}; 

//Validacion email
$("#email").blur(function()
{
    let temp = $(this).val();
    temp = temp.toLowerCase();
    var pattern = /[a-z0-9._-]+@[^ ]+\.[a-z0-9._-]{2,3}/;
    if(temp.match(pattern))
    {
        email = true;
    }
    else
    {
        email = false;
    }
});

//Control de la validacion debajo del input de contraseña
$("#password").keyup(function()
{
    $(".formato5").show();

    let temp = $(this).val();
    if(temp.length>=8)
    {
        document.getElementById("pLength").classList.replace("notValid", "valid");
    }
    else
    {
        document.getElementById("pLength").classList.replace("valid", "notValid");
    }
    if(temp.match(/[0-9]/))
    {
        document.getElementById("pNumber").classList.replace("notValid", "valid");
    }
    else{
        document.getElementById("pNumber").classList.replace("valid", "notValid");
    }
    if(temp.match(/[a-z]/))
    {
        document.getElementById("pLower").classList.replace("notValid", "valid");
    }
    else{
        document.getElementById("pLower").classList.replace("valid", "notValid");
    }
    if(temp.match(/[A-Z]/))
    {
        document.getElementById("pCap").classList.replace("notValid", "valid");
    }
    else{
        document.getElementById("pCap").classList.replace("valid", "notValid");
    }
    if(temp.match(/[.\,\;\:\\'\"\«\»\—\-\_\¡\!\¿\?]/))
    {
        document.getElementById("pSign").classList.replace("notValid", "valid");
    }
    else{
        document.getElementById("pSign").classList.replace("valid", "notValid");
    }
});