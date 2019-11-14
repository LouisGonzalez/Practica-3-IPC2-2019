<%-- 
    Document   : tarifa-cobro-cirugia
    Created on : 13/11/2019, 10:50:23 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Tarifas"%>
<%@page import="practica3.funcionesRecursosHumanos.TarifasCirugiasDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-recursos-humanos.jsp'/>
        <%
            TarifasCirugiasDAO dao = new TarifasCirugiasDAO();
            Tarifas tarifa = new Tarifas();
            ArrayList<Tarifas> listar = dao.listarCobroCirugia();
        %>
        <div class="container"><br><br>
            <h1>Precios por cirugia</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Descripcion</th>
                        <th scope="col">Total</th>
                        <th scope="col">Nuevo total</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Tarifas listar2 : listar){
                                tarifa = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=tarifa.getId()%></td>
                        <td><%=tarifa.getDescripcion()%></td>
                        <td><%=tarifa.getTotal()%></td>
                        <form action="ControladorTarifas?id=<%=tarifa.getId()%>" method="POST">
                            <td><input type="number" name="nuevaTarifa" step=".01" class="form-control" required></td>
                            <td>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Actualizar precios por operacion">Actualizar precios por operacion</button>                                                             
                            </td>
                        </form>
                    </tr>
                    <%
                            }
                        }    
                    %>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
