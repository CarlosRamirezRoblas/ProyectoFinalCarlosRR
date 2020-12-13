<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Dentista</title>
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

    <div class="m-5">
        <form class="form" role="form" method="post" action="Redirecciones">
            <div class="row">
                <div class="col-12">
                    <div class="d-flex justify-content-center">
                        <p class="text-success">${mensaje}</p>
                    </div>
                </div>
            </div>
                    <h3>Menu</h3>
            <div class="row">
                <div class="col-12">
                    <table class="table table-responsive table-borderless">
                        <tbody>
                            <tr>
                                <td><input type="submit" class="btn btn-outline-info" name="enviar" value="Asignar tratamiento"/></td>
                                <th>${userConectado.apellidos}, ${userConectado.nombre}</th>
                            </tr>
                            <tr>
                                <td> <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver historiales"/></td>
                                <td><i>DNI:</i> ${userConectado.dni}</td>
                            </tr>
                            <tr>
                                <td><input type="submit" class="btn btn-outline-info" name="enviar" value="Desasignar paciente"/></td>
                                <td><i>Correo electronico:</i> ${userConectado.email}</td>
                            </tr>
                             
                            <tr>
                                 <td><input type="submit" class="btn btn-outline-info" name="enviar" value="Asignar paciente"/></td>
                                <td><i>Ultimo acceso:</i> <fmt:formatDate type = "date" value = "${userConectado.ultimoAcceso}" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" class="btn btn-outline-info" name="enviar" value="Cambiar datos"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>