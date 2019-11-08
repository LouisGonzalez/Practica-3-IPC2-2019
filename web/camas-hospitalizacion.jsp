<%-- 
    Document   : camas-hospitalizacion
    Created on : 6/11/2019, 11:43:58 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Camas"%>
<%@page import="practica3.funcionesConsultor.CamasHabitacionesDAO"%>
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
        int idHabitacion = (int) session.getAttribute("idHabitacion");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <%
            CamasHabitacionesDAO dao = new CamasHabitacionesDAO();
            Camas cama = new Camas();
            ArrayList<Camas> listar = dao.listarCamas(idHabitacion);
        %>
        <div class="container"><br><br>
            <h1>Camas de la habitacion no. <%=idHabitacion%></h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id.</th>
                        <th scope="col">Id. Habitacion</th>
                        <th scope="col">Estado</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Camas listar2 : listar){
                                cama = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=cama.getId()%></td>
                        <td><%=cama.getId_habitacion()%></td>
                        <td><%=cama.getEstado()%></td>
                        <td>
                            <form action="ControladorCamas?id=<%=cama.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Asignar nuevo paciente">Asignar nuevo paciente</button>                        
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
