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
            <label>Seleccione un paciente para borrarlo:</label>
            <ol>
                <c:forEach var="item" items="${pacientes}">
                    <li>-  <input type="radio" name="paciente" value="${item.idUsuario}" checked/>
                        <c:set var="salida" value="El paciente con correo '${item.email}' no tiene datos."/>
                        <c:if test="${item.nombre != null}">
                            <c:set var="salida" value="${item.apellidos}, ${item.nombre}"/>
                        </c:if>
                        <label><c:out value="${salida}" default="Ha ocurrido un error inesperado"/></label>
                    </li>
                </c:forEach>
            </ol>

            <input type="submit" class="btn btn-primary" name="enviar" value="Eliminar paciente"/>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
