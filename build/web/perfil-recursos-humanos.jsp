<%-- 
    Document   : perfil-recursos-humanos
    Created on : 20/10/2019, 11:55:47 AM
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
        %>
    </head>
    <body>
        <jsp:include page="EstilosPerfiles/estilo-cuenta-recursos-humanos.jsp"/>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
