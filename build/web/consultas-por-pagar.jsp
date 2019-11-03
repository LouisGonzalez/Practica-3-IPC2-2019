<%-- 
    Document   : consultas-por-pagar
    Created on : 1/11/2019, 10:04:29 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Consultas"%>
<%@page import="practica3.funcionesConsultor.ConsultasFinalizadasDAO"%>
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
            ConsultasFinalizadasDAO dao = new ConsultasFinalizadasDAO();
            Consultas consulta = new Consultas();
            ArrayList<Consultas> listar = dao.listarConsultasFinalizadas();
        %>
        <div class="container"><br><br>
            <h1>Consultas pendientes de pago</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id consulta</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Fecha de consulta</th>
                        <th scope="col">Id medico</th>
                        <th scope='col'></th>
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
                        <td><%=consulta.getNombres()%></td>
                        <td><%=consulta.getApellidos()%></td>
                        <td><%=consulta.getFecha_consulta()%></td>
                        <td><%=consulta.getId_empleado_medico()%></td>
                        <td>
                            <form action="ControladorConsultas?id=<%=consulta.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Cancelar cita">Cancelar cita</button>    
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
