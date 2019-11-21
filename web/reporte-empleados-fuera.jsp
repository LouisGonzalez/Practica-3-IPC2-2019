<%-- 
    Document   : reporte-empleados-fuera
    Created on : 17/11/2019, 04:25:41 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.HistorialLaboral"%>
<%@page import="practica3.reportesSupervisor.EmpleadosDespedidosDAO"%>
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
        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        String user = sesion.getUsername();
        int id = sesion.getId();
        Date fechaInicial = (Date) session.getAttribute("fecha1");
        Date fechaFinal = (Date) session.getAttribute("fecha2");
        %>
    </head>
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-recursos-humanos.jsp'/>
        <div class="container"><br><br>
            <form action="ControladorReportesRHumanos" method="POST">
                Fecha Inicial<br>
                <input type="date" name="fechaInicial" class="form-control" required>
                Fecha final<br>
                <input type="date" name="fechaFinal" class="form-control" required><br><br>
                <select name="areaTrabajo" class="form-control">
                    <%
                        Conexion login = new Conexion();
                        Connection cn = login.getConnection();
                        String CONSULTA = "SELECT * FROM Areas ORDER BY id";
                        PreparedStatement declaracionConsulta = cn.prepareStatement(CONSULTA);
                        ResultSet result = declaracionConsulta.executeQuery();
                        while (result.next()) {%>
                    <option value="<%=result.getString("nombre_area")%>"><%=result.getString("nombre_area")%></option>
                    <%}
                    %>
                </select>
                    <% session.setAttribute("area", request.getParameter("areaTrabajo")); %>
                <input type="checkbox" name="verificador" class="form-control">
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte empleados fuera">Ver reporte PDF</button>                                                                                                    
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="reporte empleados fuera html">Ver reporte HTML</button>                                                                                                    
            </form> 
            <%
                EmpleadosDespedidosDAO dao = new EmpleadosDespedidosDAO();
                HistorialLaboral historial = new HistorialLaboral();
                ArrayList<HistorialLaboral> listar = null;
                boolean verificador = (boolean) session.getAttribute("verificador");
                if(verificador == true){
                    listar = dao.listarEmpleado2();
                } else {
                    listar = dao.listarEmpleados(fechaInicial, fechaFinal, request, (String) session.getAttribute("area"), session);
                }
            %>    
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id empleado</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Area de trabajo</th>
                        <th scope="col">Tipo Contratacion</th>
                        <th scope="col">Salario Base</th>
                        <th scope="col">Salario Descuento</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(HistorialLaboral listar2 : listar){
                                historial = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=historial.getId_empleado()%></td>
                        <td><%=historial.getNombres()%></td>
                        <td><%=historial.getApellidos()%></td>
                        <td><%=historial.getArea_trabajo()%></td>
                        <td><%=historial.getTipo_contratacion()%></td>
                        <td><%=historial.getSalario_base()%></td>
                        <td><%=historial.getSalario_descuento()%></td>
                    </tr>
                    <%
                        }
                    }%>
                </tbody>
            </table>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
