<%-- 
    Document   : supervisor-inmediato
    Created on : 20/10/2019, 04:48:44 PM
    Author     : luisGonzalez
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Supervisor"%>
<%@page import="practica3.DAO.SupervisorDAO"%>
<%@page import="practica3.objetos.Empleados"%>
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
        Empleados empleado = (Empleados) session.getAttribute("nuevoEmpleado");
        %>
    </head>
    <body>
        <jsp:include page="EstilosPerfiles/estilo-cuenta-administrador.jsp"/>   
        <%
            SupervisorDAO dao = new SupervisorDAO();
            Supervisor supervisor = new Supervisor();
            ArrayList<Supervisor> listar = dao.listarSupervisores(empleado.getArea_trabajo());
        %>
        <div class="container"><br><br>
            <h1>Supervisores disponibles</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id Supervisor</th>
                        <th scope="col">Nombres</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Empleados a cargo</th>
                        <th scope="col">Limite de empleados</th> 
                        <th scope="col">Asignar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int x = 0;
                        if(listar.size() > 0){
                            for(Supervisor listar2: listar){
                                supervisor = listar2;
                                x++;
                    %>
                    <tr>
                        <td scope="row"><%=x%></td>
                        <td><%=supervisor.getId()%></td>
                        <td><%=supervisor.getNombres()%></td>
                        <td><%=supervisor.getApellidos()%></td>
                        <td><%=supervisor.getPersonas_a_cargo()%></td>
                        <td><%=supervisor.getLimite_personas()%></td>
                        <td>
                            <form action="ControladorContratacion?cui=<%=supervisor.getId()%>" method="POST">
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"  name="accion" value="Asignar supervisor">Asignar Supervisor</button>
        
                            </form>
                        </td>
                    </tr>
                        <%}
                    }%>
                </tbody>
            </table>
        </div>
        
        
        <jsp:include page="EstilosPerfiles/scripts.html"/>
    </body>
</html>
