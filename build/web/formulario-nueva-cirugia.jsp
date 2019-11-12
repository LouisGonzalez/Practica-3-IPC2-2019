<%-- 
    Document   : formulario-nueva-operacion
    Created on : 10/11/2019, 06:45:08 PM
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <div class="container"><br><br>
            <h1>Formulario para una nueva cirugia</h1>
            <form action="ControladorCirugias" method="POST">
                <select name="descripcion" class="form-control">
                <%
                    Conexion login = new Conexion();
                    Connection cn = login.getConnection();
                    String CIRUGIA = "SELECT * FROM Tarifas_cirugias ORDER BY id";
                    PreparedStatement declaracionCirugia = cn.prepareStatement(CIRUGIA);
                    ResultSet result = declaracionCirugia.executeQuery();
                    while(result.next()){%>
                    <option value="<%=result.getString("descripcion")%>"><%=result.getString("descripcion")%></option>
                    <%}
                %>
                </select><br>
                <input type="date" name="fecha" class="form-control" required>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Registrar operacion en proceso">Registrar operacion en proceso</button>                                                                                        
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
