<%@page import="practica3.objetos.SesionEmpleados"%>
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
                        <a class="nav-link" href="perfil-recursos-humanos.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="contratacion-personal.jsp">Contratacion Personal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="tarifa-cirugia.jsp">Registrar un nuevo tipo de cirugia</a>
                    </li>        
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Tarifas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="tarifa-pagos-cirugia.jsp">Tarifa de pago por cirugia</a>
                            <a class="dropdown-item" href="tarifa-cobro-cirugia.jsp">Tarifa de cobro por cirugia</a>
                            <a class="dropdown-item" href="tarifa-costo-cirugia.jsp">Tarifa de costo por cirugia</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="cuotas-estadia-hospital.jsp">Precio/costo por estadia dentro del hospital</a>
                            <a class="dropdown-item" href="dias-vacacionales.jsp">No. dias vacacionales</a>
                            <a class="dropdown-item" href="salarios-hospital.jsp">Salarios dentro del hospital</a>                        
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reportes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="reporte-empleados-contratados.jsp">Reporte empleados contratados</a>
                            <a class="dropdown-item" href="reporte-empleados-fuera.jsp">Reporte empleados fuera de la empresa</a>
                            <a class="dropdown-item" href="reporte-medicos.jsp">Reporte Medicos</a>
                            
                        </div>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
            </div>
        </div>    
    </nav>
</header>
