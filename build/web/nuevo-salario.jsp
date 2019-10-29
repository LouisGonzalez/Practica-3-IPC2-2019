<%-- 
    Document   : nuevo-salario
    Created on : 26/10/2019, 09:43:02 PM
    Author     : luisGonzalez
--%>

<%@page import="practica3.funcionesSupervisor.AumentoSalarios"%>
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
        AumentoSalarios aumento = new AumentoSalarios();
        String user = sesion.getUsername();
        int id_empleado = (int) session.getAttribute("idEmpleado");
        float salarioActual = aumento.salarioActual(id_empleado);
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-supervisor.jsp'/>
        <div class="container"><br><br>
            <h1>Aumento salarial</h1>
            Salario Actual: <%=salarioActual%>
            <form action="ControladorOperacionesHistorial?id=<%=id_empleado%>" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="number" id="salario" name="salario" class="form-control" placeholder="Nuevo salario" required/>                        
                    </div>
                </div>
                <input type="date" id="fecha" name="fecha" class="form-control" required><br><br>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Confirmar aumento">Confirmar aumento</button>
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
