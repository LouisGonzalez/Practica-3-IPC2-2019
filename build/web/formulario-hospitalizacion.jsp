<%-- 
    Document   : formulario-hospitalizacion
    Created on : 7/11/2019, 05:01:13 PM
    Author     : luisGonzalez
--%>

<%@page import="java.sql.*"%>
<%@page import="practica3.conexion.Conexion"%>
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
        int idCama = (int) session.getAttribute("idCama");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <div class="container"><br><br>
            <h1>Formulario de hospitalizacion</h1>
            <form action="ControladorHospitalizacion" method="POST">
                <input name="nombres" class="form-control" placeholder="Nombre del paciente" required>
                <input name="apellidos" class="form-control" placeholder="Apellidos del paciente" required>
                <input type="number" name="cui" class="form-control" placeholder="CUI" required>
                <input name="padecimiento" class="form-control" placeholder="Padecimiento" required>
                
                
                <select name="enfermeraMando" class="form-control">
                    <%
                        Conexion login = new Conexion();
                        Connection cn = login.getConnection();
                        String ENFERMERA = "SELECT * FROM Empleados WHERE area_trabajo = ?";
                        PreparedStatement decEnfermera = cn.prepareStatement(ENFERMERA);
                        decEnfermera.setString(1, "Enfermeria");
                        ResultSet result = decEnfermera.executeQuery();
                        while(result.next()){%>
                        <option value="<%=result.getInt("id")%>"><%=result.getString("nombres")+" "+result.getString("apellidos")%></option>  
                        <%}
                    %>
                </select><br><br>
                <input type="date" name="fechaIngreso" class="form-control" required>
                
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Crear historial medico">Crear historial medico</button>
            
                
                
                
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
