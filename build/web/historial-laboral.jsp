<%-- 
    Document   : historial-laboral
    Created on : 26/10/2019, 01:39:02 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.HistorialLaboral"%>
<%@page import="practica3.funcionesSupervisor.UsuariosSupervisadosDAO"%>
<%@page import="practica3.objetos.Empleados"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-supervisor.jsp'/>
        <%
            UsuariosSupervisadosDAO dao = new UsuariosSupervisadosDAO();
            HistorialLaboral historial = new HistorialLaboral();
            ArrayList<HistorialLaboral> listar = dao.listarHistorial(id_empleado);
        %>
        <div class="container"><br><br>
            <h1>Historial laboral de los empleados bajo mi cargo</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Empleado</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(HistorialLaboral listar2: listar){
                                historial = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><a href="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>"><%=historial.getId_empleado()%></a></td>
                        <td><%=historial.getNombres()%></td>
                        <td><%=historial.getApellidos()%></td>
                        <td>
                            <form action="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Aumento">Aumento Salarial</button>  
                            </form>
                        </td>
                        <td>
                            <form action="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Renuncia">Renuncia</button>
                            </form>
                        </td>
                        <td>
                            <form action="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Despido">Despido</button>
                            </form>
                        </td>
                        <td>
                            <form action="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Historial">Ver historial</button>    
                            </form>                            
                        </td>
                        <td>
                            <form action="ControladorHistorialLaboral?id=<%=historial.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Vacaciones">Fechas vacacionales</button>    
                            </form>
                        </td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
