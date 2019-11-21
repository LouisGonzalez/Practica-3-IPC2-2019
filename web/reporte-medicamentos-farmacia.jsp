<%-- 
    Document   : reporte-medicamentos-farmacia
    Created on : 16/11/2019, 07:24:37 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Medicamentos"%>
<%@page import="practica3.reportesFarmacia.ReporteMedicamentos"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <div classs="container">
            <form action="ControladorReportesFarmacia" method="POST">
                <div class="form-check-inline">
                    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte de medicamentos">Generar reporte en PDF</button>                        
                </div>
            </form>
            <%
                ReporteMedicamentos dao = new ReporteMedicamentos();
                Medicamentos medicamento = new Medicamentos();
                ArrayList<Medicamentos> listar = dao.getReporteMedicamentos();
            %>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Costo Unitario</th>
                        <th scope="col">Precio de Venta</th>
                        <th scope="col">Cant. Existencia</th>
                        <th scope="col">Limite Existencia</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if (listar.size() > 0) {
                            for (Medicamentos listar2 : listar) {
                                medicamento = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=medicamento.getId()%></td>
                        <td><%=medicamento.getNombre()%></td>
                        <td><%=medicamento.getCosto_unitario()%></td>
                        <td><%=medicamento.getPrecio_venta()%></td>
                        <td><%=medicamento.getCant_existencia()%></td>
                        <td><%=medicamento.getLimite_existencia()%></td>
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
