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
        <a class="navbar-brand btn btn-outline-info" href="Inicio">Inicio</a>
        <form class="form" role="form" method="post" action="Acceso">
            <input type="submit" class="btn btn-primary btn-block" name="login" value="Logout"/>
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
                <div class="col-8">
                    <table class="table table-responsive table-borderless">
                        <tbody>
                            <tr>
                                <td> <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver tratamiento"/> </td>
                                <th>${userConectado.apellidos}, ${userConectado.nombre}</th>
                            </tr>
                            <tr>
                                <td> <input type="submit" class="btn btn-outline-info" name="enviar" value="Ver historial"/> </td>
                                <td><i>DNI:</i> ${userConectado.dni}</td>

                            </tr>
                            <tr>
                                <td><input type="submit" class="btn btn-outline-info" name="enviar" value="Pedir cita"/>  </td>
                                <td><i>Correo electronico:</i> ${userConectado.email}</td>

                            </tr>

                            <tr>
                                <td> <input type="submit" class="btn btn-outline-info" name="enviar" value="Cambiar datos propios"/> </td>
                                <td><i>Ultimo acceso:</i> <fmt:formatDate type = "date" value = "${userConectado.ultimoAcceso}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-4">
                    <fmt:formatDate type = "date" value = "${userConectado.cita.cita}" var="fechaFormateada" />
                    <c:if test="${fechaFormateada == null}">
                        <c:set value="No tienes." var="fechaFormateada"/>
                    </c:if>
                    <h6><c:out value="Cita: ${fechaFormateada}" default="No  tiene cita asginada."/> </h6>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>