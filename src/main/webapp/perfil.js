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
        document.getElementById('FP').src = formato + data.photo;
        document.getElementById('FPHeader').src = formato + data.photo;
        document.getElementById("navbarUsername").innerHTML = data.user.username;
        document.getElementById('username').innerHTML = data.user.username;
        document.getElementById('email').innerHTML = data.user.email;
        document.getElementById('fullname').innerHTML = data.user.firstName + " " + data.user.lastName;
        document.getElementById('age').innerHTML = calculateAge(data.user.birthdate);
    }
    else
    {
        //alert("No hay sesion activa");
        window.location.href = "index.html";
    }
}).fail(function(jqXHR, textEstado){
    alert("la solicitud ha regresado un error: " + textEstado);
    //window.location.href = "index.html";
});


function calculateAge(bdate)
{
    var year = parseInt(bdate.slice(0,4));
    var month = parseInt(bdate.slice(5,7));
    var day = parseInt(bdate.slice(8,10));
    let temp = new Date();
    let actualYear = temp.getFullYear();
    let actualMonth = temp.getMonth()+1;
    let actualDay = temp.getDate();
    var age = actualYear - year;
    if(actualMonth < month)
    {
        age = age - 1;
    }
    else
    {
        if(actualMonth == month)
        {
            if(actualDay < day)
            {
                age = age - 1;
            }
        }
    }
    return age;
}


