<%-- 
    Document   : consultas-pendientes
    Created on : 31/10/2019, 11:34:39 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Consultas"%>
<%@page import="practica3.funcionesMedico.ConsultasPendientesDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            ConsultasPendientesDAO dao = new ConsultasPendientesDAO();
            Consultas consulta = new Consultas();
            ArrayList<Consultas> listar = dao.listarConsultas(id_empleado);
        %>
        <div class="container"><br><br>
            <h1>Consultas Activas</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Cita</th>
                        <th scope="col">CUI</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Consultas listar2 : listar){
                                consulta = listar2;
                                x++;
                    %>
                    <tr>
                        <td><%=x%></td>
                        <td><%=consulta.getId()%></td>
                        <td><%=consulta.getCui()%></td>
                        <td><%=consulta.getNombres()%></td>
                        <td><%=consulta.getApellidos()%></td>
                        <td>
                            <form action="ControladorConsultas?id=<%=consulta.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Concluir cita">Concluir cita</button>
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
