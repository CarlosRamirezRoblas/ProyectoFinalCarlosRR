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
        <a class="navbar-brand" href="#"></a>
        <form class="form" role="form" method="post" action="Acceso">
            <input type="submit" class="btn btn-primary btn-block" name="login" value="Logout"/>
            </div>
        </form>
    </nav>

    <div class="m-5">
        <form class="form" role="form" method="post" action="Redirecciones">
            <div class="row">
                <div class="col-sm-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Desasignar pacientes"/>   
                </div>
                <div class="col-sm-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Eliminar paciente"/>   
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Cambiar datos"/>   
                </div>
                 <div class="col-sm-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Asignar paciente"/>   
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Asignar tratamiento"/>   
                </div>
                 <div class="col-sm-6">
                    <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver historiales"/>   
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
            <div class="row">
                <div class="col-3">
                    <img class="img-fluid" src="img/${userConectado.avatar}" alt="Avatar">
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
                                <td>${userConectado.rol}</td>
                            </tr>
                            <tr><td><fmt:formatDate type = "date" value = "${userConectado.ultimoAcceso}" /></td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>