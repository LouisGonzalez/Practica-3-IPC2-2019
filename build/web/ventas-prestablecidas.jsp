<%-- 
    Document   : ventas-prestablecidas
    Created on : 4/11/2019, 11:29:56 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Facturas"%>
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
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <%
            ListadoPreVentasDAO dao = new ListadoPreVentasDAO();
            Facturas factura = new Facturas();
            ArrayList<Facturas> listar = dao.listarPreVentas();
        %>
        <div class="container"><br><br>
            <h1>Ventas Pre-establecidas</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Factura</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Ciudad</th>
                        <th scope="col">Fecha de la factura</th>
                        <th scope="col">Nit</th>
                        <th scope="col">Id Medico</th>
                        <th scope="col">Total</th>                        
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Facturas listar2 : listar){
                                factura = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=factura.getId()%></td>
                        <td><%=factura.getNombres()%></td>
                        <td><%=factura.getApellidos()%></td>
                        <td><%=factura.getCiudad()%></td>
                        <td><%=factura.getFecha_factura()%></td>
                        <td><%=factura.getNit()%></td>
                        <td><%=factura.getId_empleado_medico()%></td>
                        <td><%=factura.getTotal()%></td>  
                        <td>
                            <form action="ControladorFacturas?id=<%=factura.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Ver compras">Ver compras</button>                        
                            </form>
                        </td>   
                        <td>
                            <form action="ControladorFacturas?id=<%=factura.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Ver compra">Ver compra</button>                                
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
