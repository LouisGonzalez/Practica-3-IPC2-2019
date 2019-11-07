<%-- 
    Document   : habitaciones-hospitalizacion
    Created on : 6/11/2019, 11:21:14 PM
    Author     : luisGonzalez
--%>

<%@page import="practica3.funcionesConsultor.HabitacionesDisponiblesDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Habitaciones"%>
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
            HabitacionesDisponiblesDAO dao = new HabitacionesDisponiblesDAO();
            Habitaciones habitacion = new Habitaciones();
            ArrayList<Habitaciones> listar = dao.listarHabitaciones();
        %>
        <div class="container"><br><br>
            <h1>Habitaciones disponibles</h1>
            <table class="table">
                <thead class="thead-dark"> 
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">No. Camas</th>
                        <th scope="col"></th>
                        
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Habitaciones listar2 : listar){
                                habitacion = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=habitacion.getId()%></td>
                        <td><%=habitacion.getNo_camas()%></td>
                        <td>
                            <form action="ControladorHabitaciones?id=<%=habitacion.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Ver camas">Ver camas</button>                    
                            </form> 
                        </td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
