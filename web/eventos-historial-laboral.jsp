<%-- 
    Document   : eventos-historiales-laborales
    Created on : 27/10/2019, 05:16:13 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.EventoHLaboral"%>
<%@page import="practica3.funcionesSupervisor.EventosHistorialesDAO"%>
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
        int idEmpleado = (int) session.getAttribute("idEmpleado");
        %>
        </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-supervisor.jsp'/>
        <%
            EventosHistorialesDAO dao = new EventosHistorialesDAO();
            EventoHLaboral evento = new EventoHLaboral();
            ArrayList<EventoHLaboral> listar = dao.listarEventos(idEmpleado);
        %>
        <div class="container"><br><br>
            <h1>Eventos</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id. Historial Laboral</th>
                        <th scope="col">Evento</th>
                        <th scope="col">Monto</th>
                        <th scope="col">Fecha del evento</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(EventoHLaboral listar2: listar){
                                evento = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=evento.getId_historial_laboral()%></td>
                        <td><%=evento.getEvento()%></td>
                        <td><%=evento.getMonto()%></td>
                        <td><%=evento.getFecha_evento()%></td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
