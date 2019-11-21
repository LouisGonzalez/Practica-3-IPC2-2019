<%-- 
    Document   : reporte-ventas-empleado
    Created on : 18/11/2019, 09:20:10 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.VentasFactura"%>
<%@page import="practica3.objetos.FacturasDTO"%>
<%@page import="java.util.List"%>
<%@page import="practica3.reportesFarmacia.ReporteVentasEmpleadoDAO"%>
<%@page import="java.sql.Date"%>
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
        Date fechaInicial = (Date) session.getAttribute("fecha1");
        Date fechaFinal = (Date) session.getAttribute("fecha2");
        int opcion = (int) session.getAttribute("opciones");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <div class="container"><br><br>
            <form action="ControladorReportesFarmacia" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input name="nombreEmpleado" class="form-control" placeholder="Filtrar por empleado">
                        <% session.setAttribute("nombreEmpleado", request.getParameter("nombreEmpleado")); %>
                        <input type="radio" name="filtro" value="opcion1" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="number" name="cuiEmpleado" class="form-control" placeholder="Filtrar por CUI">
                        <% session.setAttribute("cui", request.getParameter("cuiEmpleado")); %>
                        <input type="radio" name="filtro" value="opcion2" >
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        Mostrar reporte sin filtro alguno
                        <input type="radio" name="filtro" value="opcion3">
                    </div>
                </div>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte ventas empleado">Ver reporte en PDF</button>    
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte ventas html">Ver reporte</button>                                                                                                                                    
            </form>
            <%
                ReporteVentasEmpleadoDAO dao = new ReporteVentasEmpleadoDAO();
                List<VentasFactura> list = null;
                FacturasDTO factura = new FacturasDTO(list);
                ArrayList<FacturasDTO> listar = null;
                System.out.println(opcion);
                if(opcion == 1){
                    listar = dao.listarFiltro1((String) session.getAttribute("nombreEmpleado"));
                } else if(opcion == 2){
                    listar = dao.listarFiltro2((int) session.getAttribute("cui"));
                } else if(opcion == 3){
                    listar = dao.listarEmpleados();
                }
            %>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Empleado</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Fecha factura</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(FacturasDTO listar2 : listar){
                                factura = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=factura.getId_empleado_venta()%></td>
                        <td><%=factura.getNombre_empleado()%></td>
                        <td><%=factura.getApellido_empleado()%></td>
                        
                        <td><%=factura.getFecha_factura()%></td>
                        <td><%=factura.getTotal()%></td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
