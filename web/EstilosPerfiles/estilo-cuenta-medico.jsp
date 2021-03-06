<%@page import="practica3.objetos.SesionEmpleados"%>
<!--Navbar -->
<%
    SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
    String user = sesion.getUsername();
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
                        <a class="nav-link" href="perfil-medicos.jsp">Home <span class="sr-only">(current)</span></a>
              
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="consultas-pendientes.jsp">Consultas Activas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ControladorFacturas">Emitir una orden de compra</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pacientes-medico.jsp">Listado de pacientes</a>
                    </li>
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
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
<!--/.Navbar -->