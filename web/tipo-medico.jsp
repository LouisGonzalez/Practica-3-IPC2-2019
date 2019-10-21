<%-- 
    Document   : tipo-medico
    Created on : 20/10/2019, 07:11:54 PM
    Author     : luisGonzalez
--%>

<%@page import="practica3.objetos.Empleados"%>
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
        Empleados empleado = (Empleados) session.getAttribute("nuevoEmpleado");
        %>
    </head>
    <body>
        <jsp:include page="EstilosPerfiles/estilo-cuenta-administrador.jsp"/>   
        <form action="ControladorContratacion?cui=<%=empleado.getCUI()%>" method="POST">
            <input id="especialidad" name="especialidad" class="form-control" placeholder="Especialidad" required><br><br>
            <select name="tipoDoctor" class="form-control">
                <option value="Medico">Medico</option>
                <option value="Medico Especialista">Medico Especialista</option>
            </select><br><br>
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Guardar Medico">Guardar Medico</button>    
        </form>
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
