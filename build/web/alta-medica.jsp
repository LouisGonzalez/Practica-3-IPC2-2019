<%-- 
    Document   : alta-medica
    Created on : 12/11/2019, 11:57:45 AM
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
        int idHistorial = (int) session.getAttribute("idHMedico");
        %>
    </head>    
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <form action="ControladorHistorialMedico" method="POST">
            <div class="container"><br><br>
                <h1>Alta medica</h1>
                <input name="nombres" class="form-control" placeholder="Nombres" required>
                <input name="apellidos" class="form-control" placeholder="Apellidos" required>
                <input name="ciudad" class="form-control" placeholder="Ciudad" required>
                <input type="number" name="nit" class="form-control" placeholder="Nit" required>
                <input type="date" name="fechaSalida" class="form-control" required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Dar de alta medica">Dar de alta medica</button>                                                                                                                    
            </div>
        </form>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
