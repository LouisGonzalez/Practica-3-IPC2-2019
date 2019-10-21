<%-- 
    Document   : contratacion-personal
    Created on : 20/10/2019, 12:00:09 PM
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
        %>
    </head>
    <body>
        <jsp:include page="EstilosPerfiles/estilo-cuenta-administrador.jsp"/>
        <%=user%>        
        <form action="ControladorContratacion" method="POST">
            <input id="nombre" name="nombre" class="form-control" placeholder="Nombres" required>
            <input id="apellido" name="apellido" class="form-control" placeholder="Apellidos" required>
            <input type="number" id="edad" name="edad" class="form-control" placeholder="Edad" required>
            <input type="number" id="cui" name="cui" class="form-control" placeholder="CUI" required><br><br>
            <select name="areas" class="form-control">
                <%Conexion login = new Conexion();
                Connection cn = login.getConnection();
                String AREAS = "SELECT * FROM Areas ORDER BY id";
                PreparedStatement declaracionAreas = cn.prepareStatement(AREAS);
                ResultSet result = declaracionAreas.executeQuery();
                while(result.next()){%>
                    <option value="<%=result.getString("nombre_area")%>"><%=result.getString("nombre_area")%></option><%                                                                    
                }%>
            </select><br><br>
            <select name="tipoContratacion" class="form-control">
                <option value="Contratacion Fija">Contratacion Fija</option>
                <option value="Contratacion por servicios o temporadas">Contratacion por servicios o temporadas</option>
            </select><br><br>
            <input type="date" id="fecha" name="fecha" clas="form-control" required><br><br>
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="confirmar">Confirmar</button>
                    
        </form>
        
        
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
