<%-- 
    Document   : opciones-historial-medico
    Created on : 8/11/2019, 07:14:44 PM
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
        <div class="container"><br><br>
            <form action="ControladorHistorialMedico" method="POST">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Registrar un medicamento">Registrar el uso de un medicamento</button>                                                                        
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Registrar una operacion">Registrar una cirugia</button>                                                            
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Ver cirugias">Ver cirugias</button>                                                            
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Alta medica">Alta medica</button>                                                            
            
            </form>                                                                                
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
