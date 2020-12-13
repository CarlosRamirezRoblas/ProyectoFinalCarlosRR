<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <jsp:include page="/inc/cabecera"/>  
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand btn btn-outline-info" href="Inicio">Inicio</a>
        <button type="button" data-toggle="dropdown" class="btn btn-outline-primary dropdown-toggle">&#128100; Login</button>
        <ul class="dropdown-menu dropdown-menu-right mt-2">
            <li class="px-3 py-2">
                <div class="alert alert-warning alert-dismissable">
                    <c:out default="Introduce tus datos" value="${mensaje}"></c:out>
                    </div>
                    <form class="form" role="form" method="post" action="Registro">
                        <div class="form-group">
                            <input id="emailInput" name="email" placeholder="Email" class="form-control form-control-sm" type="text" required="true">
                        </div>
                        <div class="form-group">
                            <input  name="password" placeholder="Contrase&ntilde;a" class="form-control form-control-sm" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" name="registro" value="Registrarse"/>
                        </div>
                    </form>
                </li>
            </ul>
        </nav>
        <div class="d-flex justify-content-center">
            <p id="mensaje" class="text-danger">${mensaje}</p>
    </div>
    <form class="form" role="form" method="post" action="Registro">
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
        <input type="submit" id="enviar" class="btn btn-primary" name="registro" value="Registrarse"/>
    </form>       
    <jsp:include page="/inc/pieDePagina"/>
    <script type="text/javascript" src="${contexto}/ajax/comprobarEmail.js"></script>
</body>
</html>