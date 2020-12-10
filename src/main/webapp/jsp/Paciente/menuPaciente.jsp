<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Paciente</title>
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
            <h3>Menu de Paciente</h3>
            <div class="row">  
                <div class="col-3">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver tratamiento"/>              
                </div>
                <div class="col-4">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver historial"/>              
                </div>
            </div>
            <div class="row">  
                <div class="col-3 col-md-offset-4">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Pedir cita"/>              
                </div>
            </div>
            <div class="row">
                <div class="col-8">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Cambiar datos propios"/>              
                </div>
                <div class="p-5">
                    <div class="row">
                        <div class="col-12">
                            <div class="d-flex justify-content-center">
                                <p class="text-success">${mensaje}</p>
                            </div>
                        </div>
                    </div>
                        <div class="col-9">
                            <table class="table table-responsive table-borderless">
                                <tbody>
                                    <tr>
                                        <th scope="row">Id: ${userConectado.idUsuario}</th>
                                        <td>${userConectado.nombre}</td>
                                        <td>${userConectado.apellidos}</td>
                                    </tr>
                                    <tr>
                                        <td scope="row">${userConectado.email}</td>
                                        <td>Ultimo Acceso: <fmt:formatDate type = "date" value = "${userConectado.ultimoAcceso}" /></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>