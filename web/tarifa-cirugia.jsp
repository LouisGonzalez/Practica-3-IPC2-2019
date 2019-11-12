<%-- 
    Document   : tarifa-cirugia
    Created on : 11/11/2019, 11:47:17 PM
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
        <form action="ControladorCirugias" method="POST">
            <div class="container"><br><br>
                <h1>Nuevo tipo de cirugia</h1>
                <input name="descripcion" class="form-control" placeholder="Descripcion" required>
                <input type="number" step=".01" name="costo" class="form-control" placeholder="Costo de la cirugia" required>
                <input type="number" step=".01" name="precio" class="form-control" placeholder="Precio de la cirugia" required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Registrar operacion">Registrar operacion</button>                                                                                                    
            </div>
        </form>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
