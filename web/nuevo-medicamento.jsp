<%-- 
    Document   : nuevo-medicamento
    Created on : 31/10/2019, 12:36:05 AM
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
        <script>
            function mensaje(){
                alert('Medicamento creado con exito');
            }
        </script>    
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <div class='container'><br><br>
            <h1>Formulario para la creacion de un nuevo medicamento</h1><br>
            <form action="ControladorMedicamentos" method="POST">
                <input id="nombre" name="nombre" class="form-control" placeholder="Nombre del medicamento" required><br>
                <textarea name="descripcion" class="form-control" rows="5" cols="85" spellcheck="true" placeholder="Agrega una descripcion del medicamento"></textarea><br>
                <input type="number" name="costoUnitario" step='.01' class="form-control" placeholder="Costo unitario" required>
                <input type="number" name="precioVenta" step='.01' class="form-control" placeholder="Precio de venta" required>
                <input type='number' name='existencia' class='form-control' placeholder="Cant existencia" required>
                <input type='number' name='limiteExistencia' class='form-control' placeholder='Limite de existencia' required><br>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" onclick="mensaje()" value="Guardar medicamento">Guardar medicamento</button>

            </form>
        </div>    
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
