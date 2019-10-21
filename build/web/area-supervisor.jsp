<%-- 
    Document   : area-supervisor
    Created on : 20/10/2019, 04:08:42 PM
    Author     : luisGonzalez
--%>

<%@page import="practica3.objetos.Empleados"%>
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
        Empleados empleado = (Empleados) session.getAttribute("nuevoEmpleado");
        %>
    </head>
    <body>
        <jsp:include page="EstilosPerfiles/estilo-cuenta-administrador.jsp"/>    
        <form action="ControladorContratacion?cui=<%=empleado.getCUI()%>" method="POST">
            Area donde trabajara:
            <select name="areaSupervisor" class="form-control">
                <%Conexion login = new Conexion();
                Connection cn = login.getConnection();
                String AREAS = "SELECT * FROM Areas ORDER BY id";
                PreparedStatement declaracionAreas = cn.prepareStatement(AREAS);
                ResultSet result = declaracionAreas.executeQuery();
                while(result.next()){%>
                    <%if(!result.getString("nombre_area").equals("Administracion") && !result.getString("nombre_area").equals("Recursos Humanos") && !result.getString("nombre_area").equals("Supervision")){%>
                        <option value="<%=result.getString("nombre_area")%>"><%=result.getString("nombre_area")%></option><%                                                                    
                    }
                }%>
            </select><br><br>
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Confirmar Area">Confirmar</button>
            
        </form>
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
