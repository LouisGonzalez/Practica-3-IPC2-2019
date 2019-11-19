<%-- 
    Document   : reporte-ventas-empleado
    Created on : 18/11/2019, 09:20:10 PM
    Author     : luisGonzalez
--%>

<%@page import="practica3.objetos.SesionEmpleados"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel='stylesheet' href='estilosCss/estilo.css'> 
        <%
        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        String user = sesion.getUsername();
        int id = sesion.getId();
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <div class="container"><br><br>
            <form action="ControladorReportesFarmacia" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input name="nombreEmpleado" class="form-control" placeholder="Filtrar por empleado">
                        <input type="radio" name="filtro" value="opcion1" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="number" name="cuiEmpleado" class="form-control" placeholder="Filtrar por CUI">
                        <input type="radio" name="filtro" value="opcion2" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        Mostrar reporte sin filtro alguno
                        <input type="radio" name="filtro" value="opcion3">
                    </div>
                </div>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte ventas empleado">Ver reporte</button>                                                                                                                    
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
