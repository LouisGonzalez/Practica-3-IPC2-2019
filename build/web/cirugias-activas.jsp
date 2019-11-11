<%-- 
    Document   : cirugias-activas
    Created on : 10/11/2019, 11:24:29 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica3.objetos.Cirugias"%>
<%@page import="practica3.funcionesMedico.CirugiasActivasDAO"%>
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
        %>
    </head>    
    <body>
        <jsp:include page='EstilosPerfiles/estilo-cuenta-medico.jsp'/>
        <%
            CirugiasActivasDAO dao = new CirugiasActivasDAO();
            Cirugias cirugia = new Cirugias();
            ArrayList<Cirugias> listar = dao.listarCirugias();
        %>
        <div class="container"><br><br>
            <form action="ControladorCirugias" method="POST">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id</th>
                            <th scope="col">Id Historial</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Fecha cirugia</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int x = 0;
                            if (listar.size() > 0) {
                                for (Cirugias listar2 : listar) {
                                    cirugia = listar2;
                                    x++;
                        %>
                        <tr>
                            <td scope="row"><%=x%></td>
                            <td><%=cirugia.getId()%></td>
                            <td><%=cirugia.getId_historial_medico()%></td>
                            <td><%=cirugia.getTipo_operacion()%></td>
                            <td><%=cirugia.getFecha_cirugia()%></td>
                            <%
                                if (cirugia.getEstado().equals("ACTIVA")) {
                            %>
                            <td>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Marcar como finalizada">Marcar como finalizada</button>                                                                                                                    
                            </td>
                            <td>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Asignaciones">Asignaciones</button>                                                                                        
                            </td>
                                <%}
                                session.setAttribute("idCirugia"+x, cirugia.getId()); 
                                session.setAttribute("filas", x);
                            %>
                                

                        </tr>
                        <%}
                            }%>
                    </tbody>
                </table>
            </form>
        </div>
        <jsp:include page='EstilosPerfiles/scripts.html'/>
    </body>
</html>
