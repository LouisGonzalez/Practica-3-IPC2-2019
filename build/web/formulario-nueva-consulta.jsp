<%-- 
    Document   : formulario-nueva-consulta
    Created on : 31/10/2019, 10:24:38 PM
    Author     : luisitopapurey
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
        int id_empleado = sesion.getId();
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <div class="container"><br><br>
            <h1>Formulario de datos:</h1><br><br>
            <form action="ControladorConsultas" method="POST">
                <input type="number" name="cui" class="form-control" placeholder="CUI" required>
                <input name="nombres" class="form-control" placeholder="Nombres" required>
                <input name="apellidos" class="form-control" placeholder="Apellidos" required>
                <input type="date" name="fechaConsulta" class="form-control" required><br><br>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Confirmar cita">Confirmar cita</button>   
            </form>
            
            
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
