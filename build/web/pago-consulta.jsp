<%-- 
    Document   : pago-consulta
    Created on : 1/11/2019, 11:27:52 AM
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
        int id_empleado = sesion.getId();
        String nombrePaciente = (String) session.getAttribute("nombres");
        String apellidoPaciente = (String) session.getAttribute("apellidos");
        int id_medico = (int) session.getAttribute("idMedico");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <div class='container'><br><br>
            <h1>Factura por Consulta</h1>
            <form action='ControladorFacturas' method='POST'>
                <input name='nombres' class='form-control' value='<%=nombrePaciente%>' required>
                <input name='apellidos' class='form-control' value='<%=apellidoPaciente%>' required>
                <input name='nit' class='form-control' placeholder="NIT" required>
                <input name='ciudad' class='form-control' placeholder="Ciudad" required>
                <input name='idMedico' class='form-control' value='<%=id_medico%>' required>
                <input type='date' name='fechaPago' class='form-control' required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Pagar consulta">Pagar consulta</button>    
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
