<%-- 
    Document   : pacientes-enfermera
    Created on : 8/11/2019, 06:13:22 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.HistorialMedico"%>
<%@page import="practica3.funcionesEnfermeria.PacientesACargoDAO"%>
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
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            PacientesACargoDAO dao = new PacientesACargoDAO();
            HistorialMedico historial = new HistorialMedico();
            ArrayList<HistorialMedico> listar = dao.listarPacientes(id);
        %>
        <br><br>
            <h1>Listado pacientes bajo mi cargo</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Historial</th>
                        <th scope="col">CUI</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Padecimiento</th>
                        <th scope="col">No cama</th>
                        <th scope="col">No Habitacion</th>
                        <th scope="col">Fecha de inicio</th>
                        <th scope="col">Total acumulado</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(HistorialMedico listar2 : listar){
                                historial = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=historial.getId()%></td>
                        <td><%=historial.getCui_paciente()%></td>
                        <td><%=historial.getNombres()%></td>
                        <td><%=historial.getApellidos()%></td>
                        <td><%=historial.getPadecimiento()%></td>
                        <td><%=historial.getNo_cama()%></td>
                        <td><%=historial.getNo_habitacion()%></td>
                        <td><%=historial.getFecha_historial_medico()%></td>
                        <td><%=historial.getTotal()%></td>
                        <td>
                            <form action="ControladorHistorialMedico?id=<%=historial.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Opciones historial">Opciones Historial</button>                                                            
                            </form>
                        </td>
                        <td>
                            <form action="ControladorHistorialMedico?id=<%=historial.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Ver historial">Ver historial</button>                                                                                            
                            </form>
                        </td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
