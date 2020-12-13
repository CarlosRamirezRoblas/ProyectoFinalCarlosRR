<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="p-5">
        <form class="form" role="form" method="post" action="Operaciones">
             <label>Seleccione un paciente:</label>
            <select class="form-control" name="paciente">
                <c:forEach var="item" items="${pacientes}">
                    <option value="${item.idUsuario}">${item.apellidos}, ${item.nombre}</option>
            </c:forEach>
                    </select>
             </br>
                <input type="submit" class="btn btn-primary" name="enviar" value="Desasignar"/>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
