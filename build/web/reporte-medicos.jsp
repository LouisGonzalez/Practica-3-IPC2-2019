<%-- 
    Document   : reporte-medicos
    Created on : 17/11/2019, 07:02:09 PM
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-recursos-humanos.jsp'/>
        <div class="container"><br><br>
            <form action="ControladorReportesRHumanos" method="POST">
                Reporte Medicos General
                <input type="radio" name="verificador" value="opcion1" class="form-control">
                Reporte medicos asignados a un paciente
                <input type="radio" name="verificador" value="opcion2" class="form-control">
                Reporte medicos sin asignacion alguna
                <input type="radio" name="verificador" value="opcion3" class="form-control">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte medicos">Ver reporte</button>                                                                                                                    
            </form>          
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
