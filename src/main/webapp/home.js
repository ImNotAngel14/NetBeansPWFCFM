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
        //document.getElementById('UserPubli').innerHTML = data.user.username;
    }
    else
    {
        //alert("No hay sesion activa");
        window.location.href = "index.html";
    }
}).fail(function(jqXHR, textEstado){
    alert("la solicitud ha regresado un error: " + textEstado);
});

$.ajax({
    async: false,
    type: "GET",
    dataType: "json",
    url: "GetAllPosts"
}).done(function(data, textEstado, jqXHR){
    console.log(data);
    loadPosts(data);
//    if(data.Response == true)
//    {
//        const formato = "data:image/png;base64,";
//        document.getElementById('FPHeader').src = formato + data.photo;
//        document.getElementById('UserName').innerHTML = data.user.username;
//        document.getElementById('UserPubli').innerHTML = data.user.username;
//    }
//    else
//    {
//        //alert("No hay sesion activa");
//        window.location.href = "index.html";
//    }
}).fail(function(jqXHR, textEstado){
    alert("la solicitud ha regresado un error: " + textEstado);
});

$(document).ready(function()
{
    $("#profileBtn").click(function()
    {
        window.location.href = "perfil.html";
    });
    $(".reactB").click(function()
    {
//       alert("Entre");
//       alert(this.id);
    });
});

function loadPosts(data)
{
    alert(data.posts.length);
    for(let i = 0; i < data.posts.length; i++)
    {
        //////////////
        //if spoiler create button to hide image
        ////////////////
        let str = '<div class="Cpadding"><div class="PubliIndiv">';
        str+='<div class="Reaccion">';
        str+='<div><div><button class="reactB ' + data.posts[i].postId + '"';
        str+='><img src="Img/VotoUp.png" alt=""></button></div>';
        str+='<div><span>&nbsp;&nbsp;&nbsp;&nbsp;0';
        str+='</span>';
        str+='</div></div></div>';
        str+='<div class="Publi"><div class="PubliData">';
        str+='<span class="UserP">' + data.posts[i].username + '&nbsp;&nbsp;</span>';
        str+='<span class="FechaP">' + data.posts[i].uploadDate + '</span>';
        str+='</div>';
        str+='<div class="DescriP">';
        str+='<p>' + data.posts[i].postText + '</p>';
        str+='<br>';
        str+='<img class="pngP" src="data:image/png;base64,'+ data.posts[i].base64PostImage + '">';
        str+='</div><br><br>';
        str+='<div class="FuncP">';
        str+='<div class="float-star">';
        str+='<button class="Fbtn" onclick="showComents()">';
        str+='<img src="Img/Coment.png" class="icono">';//0
        str+='</button>&nbsp;';
        str+='</div></div><br><br><br>';
        str+='<div class="Comentario" style="display: none;"><br><p>Escribe tu comentario:</p>';
        str+='<input type="text" class="InputComent" placegolder="¿Qué estás pensando?...">';
        str+='<br><br>';
        str+='<button class="BtnComent">Publicar</button>';
        str+='</div></div></div></div>';
        
//        let str = '<div class="publicacion">';
//        str+='<div class="reaccion">';
//        str+='<div>';
//        str+='<div>';
//        str+='<button class="reactB">';
//        str+='<img src="Img/VotoUp.png">';
//        str+='</button>';
//        str+='</div>';
//        str+='<div>';
//        str+='<span id="contador'+i+'">&nbsp;&nbsp;&nbsp;&nbsp;' + 0 + '</span>';
//        str += '</div>';
//        str+='<div>';
//        str+='<button class="reactB">';
//        str+='<img src="Img/VotoDown.png">';
//        str+='</button>';
//        str+='</div></div></div>';
//        str+='<div class=post>';
//        str+='<div class=PubliData">';
//        str+='<span class="UserP">' + data.posts[i].username + '</span>';
//        str+='<span class="fechaP">' + data.posts[i].uploadDate + '</span>';
//        str+='</div>';
//        str+='<div class="DescriP">';
//        str+='<p>' + data.posts[i].postText + '</p>';
//        str+='<br>';
//        str+='<img src="data:image/png;base64,' + data.posts[i].base64PostImage + '">';
//        str+='</div>';
//        str+='<br>';
//        str+='<br>';
$("#Posts").append(str);
    }
}

function showComents(){ 
    $(".Comentario").show();
                } 