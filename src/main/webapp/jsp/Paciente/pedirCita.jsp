<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="d-flex justify-content-center">
            <p id="mensaje" class="text-danger">${mensaje}</p>
        </div>
        <form class="form" role="form" method="post" action="Operaciones">

            <h2><b>Pedir cita:</b></h2>
            <div class="row">
                <div class="col-7 offset-sm-1">
                    <p>Primero debe seleccionar que tipo de cita desea y el dia.</p>
                </div>
                <div class="col-4">
                    <p><b>Fines de semana cerramos.</b></p>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label>Seleccione que tipo de consulta desea realizar:</label>
                    <select class="form-control" name="operacion">
                        <option value="${operacion.revision}" selected="true">Revision</option>
                        <option value="${operacion.extraccion}">Extraccion</option>
                        <option value="${operacion.ortodoncia}">Ortodoncia</option>
                        <option value="${operacion.endodoncia}">Endodoncia</option>
                        <option value="${operacion.limpiezaDental}">Limpieza dental</option>
                    </select> 
                </div>
                <div class="col-6">
                    <label>Seleccione que dia desea</label>
                    <input  class="form-control" type="date" name="cita"  id="cita"  value=<fmt:formatDate pattern = "yyyy-MM-dd" value = "${cita.cita}"/> required/> 
                </div>
            </div>
                    <div class="p-5">
                <input type="submit" class="btn btn-primary" id="enviar" name="enviar" value="Confirmar cita"/>
            </div>
        </form>
    </div>
                    
    <jsp:include page="/inc/pieDePagina"/>
    
    <script type="text/javascript" src="${contexto}/ajax/comprobarFecha.js"></script>
</body>
</html>

