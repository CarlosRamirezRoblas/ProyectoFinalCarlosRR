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
                    <form class="form" role="form" method="post" action="Acceso">
                        <div class="form-group">
                            <input id="emailInput" name="email" placeholder="Email" class="form-control form-control-sm" type="text" required="true">
                        </div>
                        <div class="form-group">
                            <input id="passwordInput" name="password" placeholder="Password" class="form-control form-control-sm" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" name="login" value="Login"/>
                        </div>
                    </form>
                </li>
            </ul>
        </nav>

    <div class="p-3">
        <div class="d-flex justify-content-center">
            <p id="mensaje" class="text-danger">${mensaje}</p>
        </div>
        <form enctype="multipart/form-data" class="form" role="form" method="post" action="Operaciones">
            <div class="form-group">
                <label>Email del nuevo dentista</label>
                <input type="email" name="email" id="email" value="" placeholder="dentista@gmail.com" required/>  
            </div>
            <div class="form-group">
                <label>Contrase&ntilde;a del nuevo dentista</label>
                <input type="password" name="password" value="" placerholder="123" required=""/>  
            </div>
            <div class="p-5">
                <input type="submit" class="btn btn-primary" id="enviar" name="enviar" value="Cambiar datos"/>
            </div>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>