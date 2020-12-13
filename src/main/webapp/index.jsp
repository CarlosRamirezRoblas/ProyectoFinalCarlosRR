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
        <a class="navbar-brand btn btn-outline-info" href="Registro">&#128100; Registro</a>
        <button type="button" data-toggle="dropdown" class="btn btn-outline-primary dropdown-toggle">&#128100; Mi cuenta</button>
        <ul class="dropdown-menu dropdown-menu-right mt-2">
            <li class="px-3 py-2">
                <div class="alert alert-warning alert-dismissable">
                    <c:out default="Introduce tus datos" value="${mensaje}"></c:out>
                    </div>
                    <form class="form" role="form" method="post" action="Acceso">
                        <div class="form-group">
                            <input id="emailInput" name="email" placeholder="Correo electronico" class="form-control form-control-sm" type="text" required="true">
                        </div>
                        <div class="form-group">
                            <input id="passwordInput" name="password" placeholder="Contrase&ntilde;a" class="form-control form-control-sm" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" name="login" value="Iniciar Sesion"/>
                        </div>
                    </form>
                </li>
            </ul>
        </nav>
        <div class="d-flex justify-content-center">
            <p class="text-danger">${login}</p>
    </div>

    <div class="row">
        <div class="col-5">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="${contexto}/img/muestra1.jpg" style="height: 225px" alt="Primera imagen">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="${contexto}/img/muestra2.png" style="height: 225px" alt="Segunda imagen">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="${contexto}/img/muestra3.jpg" style="height: 225px" alt="Tercera imagen">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="col-7">
            <h2>Bienvenido</h2>
            <p class="d-flex justify-content-center">Clinica , aseguramos su sonrisa.<p>
            <p class="d-flex justify-content-center">Para consultar su historial, consultar su cita u otras gestiones haga click en 'Mi cuenta' e introduzca sus datos.</p>
        </div>
    </div>
 </br> </br>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>