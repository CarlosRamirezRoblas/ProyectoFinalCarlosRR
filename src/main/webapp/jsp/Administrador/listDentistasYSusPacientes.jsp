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
            <h4>Listado de dentistas</h4>
            <ul>
                <c:forEach var="item" items="${dentistas}">
                    <c:set var="salida" value="No ha rellenado su informacion personal."/>
                    <c:if test="${item.nombre != null}">
                        <c:set var="salida" value="Dentista: ${item.apellidos}, ${item.nombre}"/>
                    </c:if>
                    <li type="disc"><c:out value="${salida}" default="Error."/></li>
                    <ul>
                        <c:forEach var="paciente" items="${item.pacientes}">                      
                            <li><c:out value="Paciente: ${paciente.apellidos}, ${paciente.nombre}" default="Error."/></li>
                            </c:forEach>
                    </ul>
                </c:forEach>
            </ul>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
