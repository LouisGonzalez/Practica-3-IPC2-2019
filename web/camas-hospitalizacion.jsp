<%-- 
    Document   : camas-hospitalizacion
    Created on : 6/11/2019, 11:43:58 PM
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
        int idHabitacion = (int) session.getAttribute("idHabitacion");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <%=idHabitacion%>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
