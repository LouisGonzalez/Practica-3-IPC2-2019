<%-- 
    Document   : orden-compra-consulta
    Created on : 3/11/2019, 09:59:50 AM
    Author     : luisGonzalez
--%>

<%@page import="practica3.objetos.Facturas"%>
<%@page import="practica3.funcionesMedico.GeneracionFactura"%>
<%@page import="java.sql.*"%>
<%@page import="practica3.conexion.Conexion"%>
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
            GeneracionFactura factura = new GeneracionFactura();
            SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
            String user = sesion.getUsername();
            int id_empleado = sesion.getId();
            boolean verificador = (boolean) session.getAttribute("verificador");
            int x = (int) session.getAttribute("numFilas");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <div class="container"><br><br>
            <h1>Factura</h1>
            <form action="ControladorFacturas" method="POST">
                <input type="number" name="numeroFilas" class="form-control" placeholder="Numero de filas" required><br>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Numero de filas">Numero de filas</button>            
            </form><br><br>




            <form action="ControladorFacturas?idMedico=<%=id_empleado%>" method="POST">
                
                <%if (verificador == false){%>
                    <input name="nombres" class="form-control" placeholder="Nombres" required>
                    <input name="apellidos" class="form-control" placeholder="Apellidos" required>
                    <input name="ciudad" class="form-control" placeholder="Ciudad" required>
                    <input type="number" name="nit" class="form-control" placeholder="Nit" required>
                    <input type="date" name="fecha" class="form-control" required><br>
                <%} else {
                Facturas datos = (Facturas) session.getAttribute("datosPersonales");
                %>
                    <input name="nombres" class="form-control" value="<%=datos.getNombres()%>" required>
                    <input name="apellidos" class="form-control" value="<%=datos.getApellidos()%>" required>
                    <input name="ciudad" class="form-control" value="<%=datos.getCiudad()%>" required>
                    <input type="number" name="nit" class="form-control" value="<%=datos.getNit()%>">
                    <input type="date" name="fecha" class="form-control" value="<%=datos.getFecha_factura()%>"><br>
                <%}%>
                <table class="table" WIDTH="30%">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Cant.</th>
                            <th scope="col">Tipo Medicina</th>
                            <th scope="col">Medicina</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int y = 0;
                            float cant = 0;
                            for (int i = 0; i < x; i++) {
                                y++;
                        %>
                        <tr>
                            <td scope="row" WIDTH="100"><%=y%></td>
                            <td WIDTH="100">                       
                                <%if (verificador == false) {%>
                                <input type="number" name="cantMedicina<%=i%>" class="form-control" placeholder="Cant" required>
                                <%} else {%>
                                <input type="number" name="cantMedicina<%=i%>" class="form-control" placeholder="Cant" value="<%=(int) session.getAttribute("cantMedicamento" + i)%>" required>
                                <%}%>
                            </td>
                            <td WIDTH="200">
                                <select name="medicinas<%=i%>" class="form-control">
                                    <%
                                        Conexion login = new Conexion();
                                        Connection cn = login.getConnection();
                                        String consulta = "SELECT * FROM Medicamentos ORDER BY id";
                                        PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                        ResultSet result = declaracionConsulta.executeQuery();
                                        while (result.next()) {
                                            %><option value="<%=result.getString("nombre")%>"><%=result.getString("nombre")%></option>><%
                                        }
                                    %>
                               </select>                   
                            </td>
                            
                            

                            <%if (verificador == false) {%>
                                <td><input name="nombreMedicamento<%=i%>" class="form-control"></td>
                                <td><input type="number" name="totalUnitario<%=i%>" class="form-control"></td>
                            <%} else {%>
                                <td><input name="nombreMedicina<%=i%>" class="form-control" value="<%=(String) session.getAttribute("nombreMedicamento" + i)%>"></td>    
                                <td><input type="number" name="totalUnitario<%=i%>" class="form-control" value="<%=(float) session.getAttribute("totalUnitario" + i)%>"></td>
                            <%}%>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <table> 
                    <tr>
                        <td>
                            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Calcular totales">Calcular totales</button>            
                        </td>
                        <%if(verificador == false){%>
                        <td><input type="number" name="totalFinal" class="form-control"></td>
                        <%} else {
                        Facturas datos = (Facturas) session.getAttribute("datosPersonales");%>               
                            <td><input type="number" name="totalFinal" class="form-control" value="<%=datos.getTotal()%>"></td>
                        <%}%>
                    </tr>
                </table><br><br>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Crear factura">Crear factura</button>                                            
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
