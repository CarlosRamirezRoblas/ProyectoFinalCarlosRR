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
        <a class="navbar-brand btn btn-outline-info" href="Inicio">Inicio</a>
        <button type="button" data-toggle="dropdown" class="btn btn-outline-primary dropdown-toggle">&#128100; Login</button>
        <ul class="dropdown-menu dropdown-menu-right mt-2">
            <li class="px-3 py-2">
                <div class="alert alert-warning alert-dismissable">
                    <c:out default="Introduce tus datos" value="${mensaje}"></c:out>
                    </div>
                    <form class="form" role="form" method="post" action="Acceso">
                        <div class="form-group">
                            <input id="emailInput" name="email" placeholder="Email" class="form-control form-control-sm" type="text" required="true">
                        </div>
                        <div class="form-group">
                            <input id="passwordInput" name="password" placeholder="Password" class="form-control form-control-sm" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" name="login" value="Login"/>
                        </div>
                    </form>
                </li>
            </ul>
        </nav>

   
            <div class="row">
                <div class="col-3">
                    <img class="img-fluid" src="img/Logo1.png" alt="LogoSmiles">
                </div>
                <div class="col-9">
                    <h2>Bienvenido</h2>
                    <p class="d-flex justify-content-center">Para consultar su historial, consultar su cita u otras gestiones haga click en login  e introduzca sus datos.</p>
                    <p class="d-flex justify-content-center">Proyecto de fin de ciclo del cuso de Desarrollo de aplicaciones web 2020/2021p>
                </div>
            </div>

        <jsp:include page="/inc/pieDePagina"/>
    </body>
</html>