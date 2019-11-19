<%-- 
    Document   : hospitalizaciones-por-pagar
    Created on : 19/11/2019, 12:47:57 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Facturas"%>
<%@page import="practica3.funcionesConsultor.HospitalizacionPagoDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-consultor.jsp'/>
        <%
            HospitalizacionPagoDAO dao = new HospitalizacionPagoDAO();
            Facturas factura = new Facturas();
            ArrayList<Facturas> listar = dao.listarHospitalizaciones();
        %>
        <div class="container"><br><br>
            <h1>Hospitalizaciones por pagar</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Factura</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Total</th>
                        <th scope="col"></th>                        
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
                        <td><%=factura.getTotal()%></td>
                        <td>
                            <form action="ControladorFacturas?id=<%=factura.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Cancelar cuenta">Cancelar cuenta</button>                                    
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
