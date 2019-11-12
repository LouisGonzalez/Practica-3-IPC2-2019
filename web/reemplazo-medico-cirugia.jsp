<%-- 
    Document   : reemplazo-medico-cirugia
    Created on : 11/11/2019, 11:16:14 AM
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
            int idHistorial = (int) session.getAttribute("idHMedico");
            int idCirugia = (int) session.getAttribute("idCirugia");
            int idAsignacion = (int) session.getAttribute("idAsignacion");
        %>
    </head>    
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            MedicosCirugiaDAO dao = new MedicosCirugiaDAO();
            Medicos medico = new Medicos();
            ArrayList<Medicos> listar = dao.listarReemplazo(idAsignacion);
        %>
        <div class="container"><br><br>
            <h1>Reemplazo</h1>
            <form action="ControladorCirugias" method="POST">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id empleado</th>
                            <th scope="col">Nombres</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Especialidad</th>
                            <th scope="col"></th>
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
                            <td><%=medico.getId()%></td>
                            <td><%=medico.getNombres()%></td>
                            <td><%=medico.getApellidos()%></td>
                            <td><%=medico.getTipo()%></td>
                            <td><%=medico.getEspecialidad()%></td>
                            <td>
                                <input type="radio" name="reemplazo" value="<%=medico.getId()%>" class="form-control"> 
                            </td>
                            <% 
                                session.setAttribute("idEmpleado"+x, medico.getId());
                                session.setAttribute("filas", x);
                            %>
                        </tr>
                            <%}
                        }%>
                    </tbody>
                </table>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Guardar reemplazo">Guardar reemplazo</button>                                                                                                                                                                
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
