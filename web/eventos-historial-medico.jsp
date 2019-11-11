<%-- 
    Document   : eventos-historial-medico
    Created on : 10/11/2019, 01:20:49 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.EventoHMedico"%>
<%@page import="practica3.funcionesEnfermeria.EventosPacienteDAO"%>
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
        <%
            EventosPacienteDAO dao = new EventosPacienteDAO();
            EventoHMedico evento = new EventoHMedico();
            ArrayList<EventoHMedico> listar = dao.listarEventos(idHistorial);
        %>    
        <div class="container"><br><br>
            <h1>Listado de eventos</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Evento</th>
                        <th scope="col">Fecha evento</th>
                        <th scope="col">Id medicamento</th>
                        <th scope="col">Cobro</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(EventoHMedico listar2 : listar){
                                evento = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=evento.getId()%></td>
                        <td><%=evento.getEvento()%></td>
                        <td><%=evento.getFecha_evento()%></td>
                        <td><%=evento.getId_medicamento()%></td>
                        <td><%=evento.getCobro()%></td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
