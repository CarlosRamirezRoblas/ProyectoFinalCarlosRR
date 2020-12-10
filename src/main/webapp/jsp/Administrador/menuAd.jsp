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
        <a class="navbar-brand" href="#"></a>
        <form class="form" role="form" method="post" action="Acceso">
            <input type="submit" class="btn btn-primary btn-block" name="login" value="Logout"/>
        </form>
    </nav>

    <div class="m-5">
        <form class="form" role="form" method="post" action="Redirecciones">
            <h3>Menu de administrador</h3>
            <div class="row">
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Dar de alta a nuevos dentistas"/>              
                </div>
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Eliminar dentistas sin pacientes"/>              
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Listar los dentistas existentes"/>              
                </div>
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Listar los dentistas y pacientes"/>              
                </div>
            </div>
        </form>
        <div class="p-5">
            <div class="row">
                <div class="col-12">
                    <div class="d-flex justify-content-center">
                        <p class="text-success">${mensaje}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>