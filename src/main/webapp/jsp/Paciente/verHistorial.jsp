<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            </div>
        </form>
    </nav>
    <div class="p-5">
        <form class="form" role="form" method="post" action="Operaciones">
            
            <h2><b>Historial de ${paciente.nombre}</b></h2>
           <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Descripcion</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${paciente.historiales}">
                        <tr>
                            <td><fmt:formatDate type = "date" value = "${item.fechaHistorial}" /></td>
                            <td><c:out value="${item.descripcion}" default=""/></td>
                        </tr>   
                    </c:forEach>
                        </table>
        </form>
    </div>
    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
