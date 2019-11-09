<%-- 
    Document   : control-medicamentos-paciente
    Created on : 8/11/2019, 07:24:54 PM
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
        int idHistorial = (int) session.getAttribute("idHMedico");
        %>
    </head>    
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-enfermeria.jsp'/>
        <div class="container"><br><br>
            <h1>Registro de medicamentos</h1>
            <form action="ControladorHistorialMedico" method="POST">
                <select name="medicinas" class="form-control">
                    <%
                        Conexion login = new Conexion();
                        Connection cn = login.getConnection();
                        String CONSULTA = "SELECT * FROM Medicamentos ORDER BY id";
                        PreparedStatement declaracionConsulta = cn.prepareStatement(CONSULTA);
                        ResultSet result = declaracionConsulta.executeQuery();
                        while(result.next()){%>
                            <option value="<%=result.getInt("id")%>"><%=result.getString("nombre")%></option>
                        <%}
                    %>
                </select>
                <input type="number" name="cantidad" class="form-control" placeholder="Cantidad consumida" required/>
                <input type="date" name="fechaEvento" class="form-control" required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Registrar consumo">Registrar consumo</button>                                        
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
