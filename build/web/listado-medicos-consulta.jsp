<%-- 
    Document   : listado-medicos-consulta
    Created on : 31/10/2019, 11:21:15 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Medicos"%>
<%@page import="practica3.funcionesConsultor.ListadoMedicosDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <%
            ListadoMedicosDAO dao = new ListadoMedicosDAO();
            Medicos medico = new Medicos();
            ArrayList<Medicos> listar = dao.listarMedicos();
        %>
        <div class='container'><br><br>
            <h1>Listado de medicos inscritos</h1>
            <table class='table'>
                <thead class='thead-dark'>
                    <tr>
                        <th scope='col'>#</th>
                        <th scope='col'>Id empleado</th>
                        <th scope='col'>Nombres</th>
                        <th scope='col'>Apellidos</th>
                        <th scope='col'>Especialidad</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Medicos listar2 : listar){
                                medico = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope='row'><%=x%></td>
                        <td><%=medico.getId_empleado()%></td>
                        <td><%=medico.getNombres()%></td>
                        <td><%=medico.getApellidos()%></td>
                        <td><%=medico.getEspecialidad()%></td>
                        <td>
                            <form action="ControladorConsultas?id=<%=medico.getId_empleado()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Agendar una cita">Agendar una cita</button>
                
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
