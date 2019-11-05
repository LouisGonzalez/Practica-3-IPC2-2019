<%-- 
    Document   : ventas-desglosadas
    Created on : 4/11/2019, 11:56:29 PM
    Author     : luisGonzalez
--%>

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
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <%
            ListadoPreVentasDAO dao = new ListadoPreVentasDAO();
            VentasFactura ventas = new VentasFactura();
            ArrayList<VentasFactura> listar = dao.listaDesglosada(idFactura);
        %>
        <div class="container"><br><br>
            <h1>Ventas desglosadas</h1>
            <table class="table">
                <thead class="thead-dark"> 
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Factura</th>
                        <th scope="col">Id medicamento</th>
                        <th scope="col">Cant. Producto</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(VentasFactura listar2: listar){
                                ventas = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=ventas.getId_factura()%></td>
                        <td><%=ventas.getId_medicamento()%></td>
                        <td><%=ventas.getCant_producto()%></td>
                        <td><%=ventas.getTotal()%></td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
