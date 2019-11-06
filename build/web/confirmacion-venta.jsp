<%-- 
    Document   : confirmacion-venta
    Created on : 5/11/2019, 11:08:40 AM
    Author     : luisGonzalez
--%>

<%@page import="practica3.funcionesFarmaceutico.VentaMedicamentos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.VentasFactura"%>
<%@page import="practica3.funcionesFarmaceutico.ListadoPreVentasDAO"%>
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
            int idFactura = (int) session.getAttribute("idFactura");
            boolean verificador = (boolean) session.getAttribute("verificador");
            boolean iniciador = (boolean) session.getAttribute("iniciador");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <%
            ListadoPreVentasDAO dao = new ListadoPreVentasDAO();
            VentasFactura ventas = new VentasFactura();
            ArrayList<VentasFactura> listar = dao.listaDesglosada(idFactura);
            VentaMedicamentos ventaMedicina = new VentaMedicamentos();
        %>
        <form action="ControladorFacturas" method="POST">
            <div class="container"><br><br>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id</th>
                            <th scope="col">Id Factura</th>
                            <th scope="col">Id medicamento</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Cant. Producto</th>
                            <th scope="col">Cant. Producto Disponible</th>
                            <th scope="col">Total Pre-establecido</th>
                            <th scope="col">Total Final</th>   
                            <th scope="col">Cola</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int x = 0;
                            
                            
                            
                            
                            if (listar.size() > 0) {
                                for (VentasFactura listar2 : listar) {
                                    ventas = listar2;
                                    x++;
                        %>
                        <tr>
                            <td scope="row"><%=x%></td>
                            <td><%=ventas.getId()%></td>
                            <%session.setAttribute("id"+x, ventas.getId()); %>
                            <td><%=ventas.getId_factura()%></td>
                            <td><%=ventas.getId_medicamento()%></td>
                            <td><%=ventas.getDescripcion()%></td>
                            <td><%=ventas.getCant_producto()%></td>
                            <%int cantFinal = ventaMedicina.totalEnExistencia(idFactura, ventas.getId_medicamento(), ventas);%>
                            <td><%=cantFinal%></td>
                            <%session.setAttribute("cantFinal"+x, cantFinal); %>
                            <td><%=ventas.getTotal()%></td>
                            <%float totalFinal =  ventaMedicina.revisionExistencias(idFactura, ventas.getId_medicamento(), ventas);
                            session.setAttribute("totalFinal"+x, totalFinal); %>
                            <td><input name="total<%=x%>" class="form-control" value=" <%=totalFinal%>"/></td>
                            
                            
                            <td>     
                                <%if(iniciador == false) {%>                                
                                    <input type="checkbox" name="confirmacion<%=x%>">
                                <%} else if(iniciador == true){%>
                                    <%boolean checkbox = (boolean) session.getAttribute("verificadorCasilla"+x); %>                                
                                    <% if(checkbox == true) {%>    
                                        <input type="checkbox" name="confirmacion<%=x%>" checked>
                                    <%} else if(iniciador == true && checkbox == false) {%>
                                        <input type="checkbox" name="confirmacion<%=x%>">
                                    <%}
                                }%>                            
                            </td>
                                <%}
                            }
                            session.setAttribute("filas", x);
                            session.setAttribute("ventas", ventas);    %>
                           
                    </tbody>
                </table>
                <table>
                    <td>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Total final">Calcular totales</button>            
                    </td>
                    <%if(verificador == false){%>
                        <td><input type="number" name="totalFinal" class="form-control"></td>
                    <%} else {
                        float totalFinal = (float) session.getAttribute("totalFinal"); %>
                        
                        <td><input type="number" name="totalFinal" class="form-control" value="<%=totalFinal%>"></td>
                    <%}%>        
                </table><br><br>    
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Efectuar compra">Efectuar compra</button>                                            
            </div>         
        </form>        
        <jsp:include page='EstilosPerfiles/scripts.html'/>            
    </body>
</html>
