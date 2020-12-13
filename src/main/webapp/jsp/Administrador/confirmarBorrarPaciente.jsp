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
        <form class="form" role="form" method="post" action="Operaciones">
            <div class="row">
                <div class="col-11 offset-1">
                    <h5>¿Desea <i>eliminar</i> este paciente?</h5>
                </div>
            </div>

            </br></br>
            <table class="table table-striped">
                <thead>
                <th>Nombre y apellidos</th>
                <td>Correo electronico</td>
                <td>DNI</td>
                </thead>
                <tbody>
                    <tr>
                        <c:set var="salida" value="Datos vacios."/>
                        <c:if test="${paciente.nombre != null}">
                            <c:set var="salida" value="${paciente.apellidos}, ${paciente.nombre}"/>
                        </c:if>
                        <th scope="col"><c:out value="${salida}" default="El paciente no tiene datos."/></th>
                        <td scope="col"><c:out value="${paciente.email}" default="El paciente no tiene datos."/></td>
                        <td scope="col"><c:out value="${paciente.dni}" default="Datos vacios."/></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="confirmar" value="${paciente.idUsuario}"/>

            <input type="submit" class="btn btn-primary" name="enviar" value="Borrar paciente"/>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
