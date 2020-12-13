<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Administrador</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <jsp:include page="/inc/cabecera"/>  
    <nav class="navbar navbar-light bg-light">
         <a class="navbar-brand btn btn-outline-info" href="Inicio">Inicio</a>
        <form class="form" role="form" method="post" action="Acceso">
            <input type="submit" class="btn btn-primary btn-block" name="login" value="Logout"/>
            </div>
        </form>
    </nav>
    <form class="form" role="form" method="post" action="Operaciones">
        <div class="row">
            <div class="col-11 offset-1">
                <h4>Introduce tus datos para crear tu cuenta.</h4>
                </br>
            </div>
        </div>
        <div class="row">

            <div class="col-2 offset-2">
                <label>Email</label>
            </div>
            <div class="col-8">
                <input type="email" name="email" id="email" value="" placeholder="email@gmail.com" required/>  
            </div>

        </div>
        <div class="row">

            <div class="col-2 offset-2">
                <label>Contrase&ntilde;a</label>
            </div>
            <div class="col-8">
                <input type="password" name="password" value="" placerholder="Contrase&ntilde;a" required/>
            </div>    
        </div>
        </br></br>
        <input type="submit" id="enviar" class="btn btn-primary" name="enviar" value="Crear dentista"/>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
    <script type="text/javascript" src="${contexto}/ajax/comprobarEmail.js"></script>
</body>
</html>