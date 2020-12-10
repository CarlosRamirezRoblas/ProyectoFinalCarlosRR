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
    <div class="p-5">
        <div class="d-flex justify-content-center">
            <p id="mensaje" class="text-danger">${mensaje}</p>
        </div>
        <form class="form" role="form" method="post" action="Operaciones">
            <div class="form-group">
                <label>Email del nuevo dentista</label>
                <input type="email" name="email" id="email" value="" placeholder="dentista@gmail.com" required/>  
            </div>  
            <div class="form-group">
                <label>Contrase&ntilde;a del nuevo dentista</label>
                <input type="password" name="password" value="" placerholder="123" required=""/>  
            </div>
            <input type="submit" id="enviar" class="btn btn-primary" name="enviar" value="Crear dentista"/>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
    <script type="text/javascript" src="${contexto}/ajax/comprobarEmail.js"></script>
</body>
</html>