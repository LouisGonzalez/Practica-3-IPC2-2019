<%-- 
    Document   : confirmacion-renuncia
    Created on : 27/10/2019, 04:06:47 PM
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
        int id_empleado = (int) session.getAttribute("idEmpleado");
        %>
    </head>
    <body>
        <script>
            function mensaje(){
                alert('La renuncia ha sido efectuada con exito');
            }
        </script>    
        <jsp:include page='EstilosPerfiles/estilo-cuenta-supervisor.jsp'/>
        <div class="container"><br><br>
            <h1>Confirmacion de Renuncia</h1><br><br>
            <form action="ControladorOperacionesHistorial?id=<%=id_empleado%>" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="date" id="fechaRenuncia" name="fechaRenuncia" class="form-control" required><br><br>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" onclick="mensaje()" value="Confirmar renuncia">Confirmar Renuncia</button>    
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
