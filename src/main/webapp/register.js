var email = true;
$(document).ready(function()
{
    loadBirthdateInput();
   $("#NotValidEmail").hide();
   $("#NotValidPass").hide();
   $("#registerForm").submit(function(event)
    {
        event.preventDefault();
        if(isValidRegister())
        {
            $.ajax({
                data: new FormData(this),
                type:"POST",
                dataType: "json",
                url: "Register",
                cache: false,
                contentType: false,
                processData: false
            }).done(function(data){
                console.log(data);
            if(data.registered)
            {
                alert("Registrado correctamente");
                window.open('index.html', '_self');
            }
            else
            {
                alert("Ocurrió un error al registrar.");
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
    if(tMonth < 10)
    {
        tMonth = "0" + tMonth;
    }
    //let month = tMonth.toString();
    let day = temp.getDate();
    console.log("prechange = " + day);
    if(day < 10)
    {
        day = "0" + day;
        console.log("after: " + day);
    }
    var today = year+"-"+tMonth+"-"+day;
    return today;
}

function loadBirthdateInput()
{
    console.log("loadBirthdate");
    let today = getToday();
    document.getElementById("birthdate").setAttribute("value", today);
    document.getElementById("birthdate").setAttribute("max", today);
}
/////////////////////////////////////////////////
//Validacion de los input
/////////////////////////////////////////////////
function isValidRegister()
{
    /*
    if(email === false)
    {
        $("#NotValidEmail").hide();
    }
    else
    {
        $("#NotValidEmail").show();
        return false;
    }
    */
    var pass1 = $("#password").val();
    var pass2 = $("#confirmPassword").val();
    if(pass1 !== pass2)
    {
        $("#NotValidPass").show();
        return false;
    }
    return true;
}; 

//Validacion email
/*
$("#email").blur(function()
{
    let temp = $(this).val();
    temp = temp.toLowerCase();
    var pattern = /[a-z0-9._-]+@[a-z0-9.-]+\.[a-z0-9._-]{2,}$/;
    if(temp.match(pattern))
    {
        email = true;
    }
    else
    {
        email = false;
    }
});
*/

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