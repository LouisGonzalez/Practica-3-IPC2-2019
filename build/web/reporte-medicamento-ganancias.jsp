<%-- 
    Document   : reporte-medicamento-ganancias
    Created on : 18/11/2019, 10:14:27 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.List"%>
<%@page import="practica3.objetos.MedicamentosDTO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Facturas"%>
<%@page import="practica3.reportesFarmacia.ReporteVentasMedicamentosDAO"%>
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
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-farmaceutico.jsp'/>
        <div class="container">
        <form action="ControladorReportesFarmacia" method="POST">
            <div class="form-row">
                <div class="form-group col-md-4">
                    Parametro para fecha 1
                    <input type="date" name="fecha1" class="form-control" required>
                    Parametro para fecha 2 
                    <input type="date" name="fecha2" class="form-control" required>
                </div>
            </div><br>  
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="reporte ganancias medicamento">Aumento Salarial</button>   
            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="reporte ganancias medicamento html">Ver reporte</button>   
           
        </form>
            <%
                ReporteVentasMedicamentosDAO dao = new ReporteVentasMedicamentosDAO();
                List<Facturas> list = null;
                MedicamentosDTO dto = new MedicamentosDTO(list);
                 ArrayList<MedicamentosDTO> listar;
                boolean verificador  = (boolean) session.getAttribute("verificador");
                if(verificador == false){
                    listar = dao.listarMedicamentos(fechaInicial, fechaFinal);                    
                } else {
                    listar = dao.listarSinFecha();
                }
            %>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Costo Unitario</th>
                        <th scope="col">Precio de Venta</th>
                        <th scope="col">Total </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(MedicamentosDTO listar2 : listar){
                                dto = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=dto.getId()%></td>
                        <td><%=dto.getNombre()%></td>
                        <td><%=dto.getCosto_unitario()%></td>
                        <td><%=dto.getPrecio_venta()%></td>
                        <td><%=dto.getGanancia()%></td>
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
