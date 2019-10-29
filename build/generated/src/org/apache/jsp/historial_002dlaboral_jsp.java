package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import practica3.objetos.HistorialLaboral;
import practica3.funcionesSupervisor.UsuariosSupervisadosDAO;
import practica3.objetos.Empleados;
import practica3.objetos.SesionEmpleados;

public final class historial_002dlaboral_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel='stylesheet' href='estilosCss/estilo.css'> \n");
      out.write("        ");

        SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
        String user = sesion.getUsername();
        int id_empleado = sesion.getId();
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <script>\n");
      out.write("            function mensaje(){\n");
      out.write("                alert('Operacion realizada con exito');\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "EstilosPerfiles/estilo-cuenta-supervisor.jsp", out, false);
      out.write("\n");
      out.write("        ");

            UsuariosSupervisadosDAO dao = new UsuariosSupervisadosDAO();
            HistorialLaboral historial = new HistorialLaboral();
            ArrayList<HistorialLaboral> listar = dao.listarHistorial(id_empleado);
        
      out.write("\n");
      out.write("        <div class=\"container\"><br><br>\n");
      out.write("            <h1>Historial laboral de los empleados bajo mi cargo</h1>\n");
      out.write("            <table class=\"table\">\n");
      out.write("                <thead class=\"thead-dark\">\n");
      out.write("                    <tr>\n");
      out.write("                        <th scope=\"col\">#</th>\n");
      out.write("                        <th scope=\"col\">Id Empleado</th>\n");
      out.write("                        <th scope=\"col\">Nombres</th>\n");
      out.write("                        <th scope=\"col\">Apellidos</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        int x = 0;
                        if(listar.size() > 0){
                            for(HistorialLaboral listar2: listar){
                                historial = listar2;
                                x++;
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td scope=\"row\">");
      out.print(x);
      out.write("</td>\n");
      out.write("                        <td><a href=\"ControladorHistorialLaboral?id=");
      out.print(historial.getId_empleado());
      out.write('"');
      out.write('>');
      out.print(historial.getId_empleado());
      out.write("</a></td>\n");
      out.write("                        <td>");
      out.print(historial.getNombres());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(historial.getApellidos());
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <form action=\"ControladorHistorialLaboral?id=");
      out.print(historial.getId_empleado());
      out.write("\" method=\"POST\">\n");
      out.write("                                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\"  name=\"accion\" value=\"Aumento\">Aumento Salarial</button>  \n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <form action=\"ControladorHistorialLaboral?id=");
      out.print(historial.getId_empleado());
      out.write("\" method=\"POST\">\n");
      out.write("                                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\"  name=\"accion\" value=\"Renuncia\" onclick=\"mensaje()\">Renuncia</button>\n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <form action=\"ControladorHistorialLaboral?id=");
      out.print(historial.getId_empleado());
      out.write("\" method=\"POST\">\n");
      out.write("                                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\"  name=\"accion\" value=\"Despido\">Despido</button>\n");
      out.write("                            </form>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <form action=\"#\" method=\"POST\">\n");
      out.write("                                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\"  name=\"accion\" value=\"Historial\">Ver historial</button>    \n");
      out.write("                            </form>\n");
      out.write("                            \n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                        ");
}
                    }
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "EstilosPerfiles/scripts.html", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
