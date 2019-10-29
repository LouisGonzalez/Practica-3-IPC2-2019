<%-- 
    Document   : info-empleados-supervisor
    Created on : 26/10/2019, 04:46:14 PM
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
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-supervisor.jsp'/>
        <div class="container"><br><br>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Tipo:</th>
                        <th scope="col">Descripcion:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Nombres:</td>
                        <td>${info.nombres}</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Apellidos:</td>
                        <td>${info.apellidos}</td>
                        
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>No. Periodo Laboral:</td>
                        <td>${info.no_periodo_laboral}</td>
                        
                    </tr>
                    <tr>
                        <th scope="row">4</th>
                        <td>Salario base:</td>
                        <td>${info.salario_base}</td>
                        
                    </tr>
                    <tr>
                        <th scope="row">5</th>
                        <td>Salario con descuentos:</td>
                        <td>${info.salario_descuento}</td>
                        
                    </tr>
                    <tr>
                        <th scope="row">6</th>
                        <td>Fecha Historial Laboral:</td>
                        <td>${info.fecha_historial_laboral}</td>
                        
                    </tr>

                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
