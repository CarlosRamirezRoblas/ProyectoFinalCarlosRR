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

    <div class="p-3">
        <div class="d-flex justify-content-center">
            <p id="mensaje" class="text-danger">${mensaje}</p>
        </div>
        <form enctype="multipart/form-data" class="form" role="form" method="post" action="Operaciones">
            <div class="row"> 
                <div class="col-6">
                    <label>Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="${userConectado.nombre}" required/>  
                </div>  
                <div class="col-6">
                    <label>Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" value="${userConectado.apellidos}" required/> 
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label>Contrase&ntilde;a</label>
                    <input type="password" class="form-control" id="password" name="password" value="${userConectado.password}" required/>
                </div>
                <div class="col-6">
                    <label>Repite contrase&ntilde;a</label>
                    <input type="password" class="form-control" id="repitePassword" name="repitePassword" value="${userConectado.password}" required/>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label>Dni</label>
                    <input type="text" class="form-control" pattern="[0-9]{8}[A-Za-z]{1}" title="64521894k" name="dni" id="dni" value="${userConectado.dni}" required/>  
                </div>
                <div class="col-6">
                    <label>Horario</label>
                   <input type="text" class="form-control" name="horario" value="${userConectado.horario}" required/> 
                </div>
            </div>
            <div class="p-5">
                <input type="submit" class="btn btn-primary" id="enviar" name="enviar" value="Cambiar datos"/>
            </div>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
    <script type="text/javascript" src="${contexto}/ajax/comprobarPassword.js"></script>
    <script type="text/javascript" src="${contexto}/ajax/comprobarRepitePassword.js"></script>
    <script type="text/javascript" src="${contexto}/ajax/comprobarDNI.js"></script>
</body>
</html>