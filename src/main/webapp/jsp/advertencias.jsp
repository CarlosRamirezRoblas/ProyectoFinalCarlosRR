<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>
    <jsp:include page="/inc/cabecera"/>  
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="Inicio">Inicio</a>
        <form class="form" role="form" method="post" action="Acceso">
            <input type="submit" class="btn btn-primary btn-block" name="login" value="Logout"/>
        </form>
    </nav>

    <div class="p-5">
        <div class="row">
            <div class="col-3">
                <img class="img-fluid" src="img/emoji.png" alt="AvatarDesconocido">
            </div>
            <div class="col-9">
                <h4 class="d-flex justify-content-center">${mensaje}</h4>
            </div>
        </div>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>