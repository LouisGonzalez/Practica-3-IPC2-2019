<%-- 
    Document   : asignacion-medicos-cirugia
    Created on : 10/11/2019, 06:55:31 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Medicos"%>
<%@page import="practica3.funcionesMedico.MedicosCirugiaDAO"%>
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
        int idHMedico = (int) session.getAttribute("idHMedico");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            MedicosCirugiaDAO dao = new MedicosCirugiaDAO();
            Medicos medico = new Medicos();
            ArrayList<Medicos> listar = dao.listarMedicos();
        %> 
        <div class="container"><br><br>
            <h1>Asignacion de medicos</h1>
            <form action="ControladorCirugias" method="POST">
                            
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Especialidad</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    int x = 0;
                    if (listar.size() > 0) {
                        for (Medicos listar2 : listar) {
                            medico = listar2;
                            x++;
                %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=medico.getId_empleado()%></td>
                        <td><%=medico.getNombres()%></td>
                        <td><%=medico.getApellidos()%></td>
                        <td><%=medico.getEspecialidad()%></td>
                        <td><input type="checkbox" name="medico<%=x%>"></td>
                        <%
                            session.setAttribute("idMedico"+x, medico.getId_empleado());
                            session.setAttribute("filas", x);
                        %>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Asignar medicos">Asignar medicos</button>                                                                                                        
            </form>
                
        </div>
        
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
