<%-- 
    Document   : reporte-medicamento-ganancias
    Created on : 18/11/2019, 10:14:27 AM
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
        <div class="container">
        <form action="ControladorReportesFarmacia" method="POST">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <input type="date" name="fecha1" class="form-control" required>
                    <input type="date" name="fecha2" class="form-control" required>
                </div>
            </div><br>    
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="reporte ganancias medicamento">Aumento Salarial</button>                                  
        </form>
        </div>
         <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
