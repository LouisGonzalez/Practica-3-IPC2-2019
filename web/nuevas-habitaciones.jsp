<%-- 
    Document   : nuevas-habitaciones
    Created on : 6/11/2019, 09:02:13 PM
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-administrador.jsp'/>
        <div class="container"><br><br>
            <h1>Habitaciones:</h1>
            <form action="ControladorHabitaciones" method="POST">
                <input type="number" name="nuevaHabitacion" class="form-control" placeholder="Total de habitaciones" required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Crear habitaciones">Crear habitaciones</button>                                            
            
            </form>
        </div>        
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
