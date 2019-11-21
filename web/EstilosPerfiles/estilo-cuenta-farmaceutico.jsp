<%@page import="practica3.objetos.SesionEmpleados"%>
<%
    SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
    String user = sesion.getUsername();
    String gananciasMedicamentos = "reporte-medicamento-ganancias.jsp";
    String ventasEmpleado = "reporte-ventas-empleado.jsp";
%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light static-top" style="background-color: #e3f2fd;">
        <div class="container">
            <a class="navbar-brand" href="#"><%=user%></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
             
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="perfil-farmacia.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="nuevo-medicamento.jsp">Ingresar un nuevo tipo de medicina al inventario</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ventas-prestablecidas.jsp">Ventas pre-establecidas</a>
                    </li>
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reportes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="reporte-medicamentos-farmacia.jsp">Reporte de Medicamentos</a>
                            <a class="dropdown-item" href="ControladorRedireccionFarmacia?direccion=<%=gananciasMedicamentos%>">Reporte Ganancias por Medicamento</a>
                            <a class="dropdown-item" href="ControladorRedireccionFarmacia?direccion=<%=ventasEmpleado%>">Reporte Ventas por Empleado</a>
                            
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ControladorSesion">Salir</a>
                    </li>
                </ul>
            </div>
        </div>    
    </nav>
</header>
