<%-- 
    Document   : asignacion-enfermeras-paciente
    Created on : 10/11/2019, 02:14:40 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Empleados"%>
<%@page import="practica3.funcionesConsultor.TrabajadoresAsignacionDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <%
            TrabajadoresAsignacionDAO dao = new TrabajadoresAsignacionDAO();
            Empleados enfermera = new Empleados();
            ArrayList<Empleados> listar = dao.listarEnfermeras();
        %>
        <form action="ControladorHospitalizacion" method="POST">
            <div class="container"><br><br>
                <h1>Asignacion de enfermeras</h1>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id. empleado</th>
                            <th scope="col">Nombres</th>
                            <th scope="col">Apellidos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int x = 0;
                            if (listar.size() > 0) {
                                for (Empleados listar2 : listar) {
                                    enfermera = listar2;
                                    x++;
                        %>
                        <tr>
                            <td scope="row"><%=x%></td>
                            <td><%=enfermera.getId()%></td>
                            <td><%=enfermera.getNombres()%></td>
                            <td><%=enfermera.getApellidos()%></td>
                            <td><input type="checkbox" name="enfermera<%=x%>"></td>                   
                            <%
                              session.setAttribute("filas", x);
                              session.setAttribute("idEnfermera"+x, enfermera.getId()); 
                            %>

                        </tr>
                            <%}
                        }%>
                    </tbody>
                </table>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Asignar enfermeras">Asignar enfermeras</button>
            
            </div>
        </form>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
