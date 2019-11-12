<%-- 
    Document   : medicos-asignados-cirugia
    Created on : 11/11/2019, 10:06:05 AM
    Author     : luisGonzalez
--%>

<%@page import="practica3.objetos.Medicos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica3.funcionesMedico.MedicosCirugiaDAO"%>
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
            int idHistorial = (int) session.getAttribute("idHMedico");
            int idCirugia = (int) session.getAttribute("idCirugia");
        %>
    </head>    
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            MedicosCirugiaDAO dao = new MedicosCirugiaDAO();
            Medicos medico = new Medicos();
            ArrayList<Medicos> listar = dao.listarMedicosAsignados(idCirugia);
        %>
        <div class="container"><br><br>
            <h1>Listado medicos asignados</h1>
            <table class="table"> 
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Id Empleado</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Especialidad</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Medicos listar2 : listar){
                                medico = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=medico.getId()%></td>
                        <td><%=medico.getId_empleado()%></td>
                        <td><%=medico.getNombres()%></td>
                        <td><%=medico.getApellidos()%></td>
                        <td><%=medico.getTipo()%></td>
                        <td><%=medico.getEspecialidad()%></td>
                        <td>
                            <form action="ControladorCirugias?id=<%=medico.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Reemplazar">Reemplazar</button>                                                                                                                            
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
