<%-- 
    Document   : reporte-empleados-contratados
    Created on : 17/11/2019, 02:35:38 PM
    Author     : luisitopapurey
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
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-recursos-humanos.jsp'/>
        <div class="container"><br><br>
            <form action="ControladorReportesRHumanos" method="POST">
                Fecha Inicial<br>
                <input type="date" name="fechaInicial" class="form-control" required>
                Fecha final<br>
                <input type="date" name="fechaFinal" class="form-control" required><br><br>

                <select name="areaTrabajo" class="form-control">
                    <%
                        Conexion login = new Conexion();
                        Connection cn = login.getConnection();
                        String CONSULTA = "SELECT * FROM Areas ORDER BY id";
                        PreparedStatement declaracionConsulta = cn.prepareStatement(CONSULTA);
                        ResultSet result = declaracionConsulta.executeQuery();
                        while (result.next()) {%>
                    <option value="<%=result.getString("nombre_area")%>"><%=result.getString("nombre_area")%></option>
                    <%}
                    %>
                </select>
                <input type="checkbox" name="verificador" class="form-control">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte empleados contratados">Ver reporte</button>                                                                                                    
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/> 
    </body>
</html>
