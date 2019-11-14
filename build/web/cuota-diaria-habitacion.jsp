<%-- 
    Document   : cuota-diaria-habitacion
    Created on : 6/11/2019, 10:15:22 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Habitaciones"%>
<%@page import="practica3.funcionesAdministrador.ListadoHabitacionesDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-administrador.jsp'/>
        <%
            ListadoHabitacionesDAO dao = new ListadoHabitacionesDAO();
            Habitaciones habitacion = new Habitaciones();
            ArrayList<Habitaciones> listar = dao.listarHabitacionesAdmin();
        %>
        <div class="container"><br><br>
            <h1>Cambio de cuotas</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">No. Camas</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Costo diario</th>
                        <th scope="col">Modificar costo</th>
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
                        <td><%=habitacion.getEstado()%></td>
                        <td><%=habitacion.getCosto_diario()%></td>
                            <form action="ControladorHabitaciones?id=<%=habitacion.getId()%>" method="POST">
                                
                        <td><input type="number" name="nuevoTotal" step=".01" class="form-control" required></td>
                        <td>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Cambiar cuota">Cambiar cuota</button>                
                        </td>
                            </form>
                        
                    </tr>
                    <%}
                    }%>
                </tbody>
            </table>    
            
            
        </div>
        
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
