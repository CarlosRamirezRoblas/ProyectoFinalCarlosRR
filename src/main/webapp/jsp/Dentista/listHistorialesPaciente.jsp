<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="row"> 
                <div class="col-sm-11 offset-sm-1">
                    <h4>Listado de pacientes:</h4>
                </div>
            </div>
            <c:forEach var="item" items="${pacientes}">
                <div class="row"> 
                    <div class="col-sm-4">
                        <p><b>${item.apellidos}, ${item.nombre}</b></p>
                    </div>
                    <div class="col-sm-8">
                        <p><i>Tratamiento: </br> </i><c:out value="${item.tratamiento}" default="El paciente no tiene tratamiento."/> </p>
                    </div>
                </div>
                     <c:forEach var="historial" items="${item.historiales}">
                <div class="row">
                    <div class="col-sm-3">
                        <p><i> <fmt:formatDate type = "date" value = "${userConectado.ultimoAcceso}" /> </i></p>
                    </div>
                    <div class="col-sm-9">
                        <ul>
                            <c:set var="salida" value="El paciente no tiene historial."/>
                            <c:if test="${item.historiales != null}">
                               
                                    <c:set var="salida" value="${historial.descripcion}"/>
                                    <li><c:out value="${salida}"/></li> 
                                    
                                </c:if>
                                <c:if test="${salida == 'El paciente no tiene historial.'}">
                                <li><c:out value="${salida}"/></li> 
                                </c:if>
                        </ul>
                    </div>
                </div>
                            </c:forEach>
                    </br>
            </c:forEach>
        </form>
    </div>

    <jsp:include page="/inc/pieDePagina"/>
</body>
</html>
