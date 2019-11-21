package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import practica3.funcionesSupervisor.AumentoSalarios;
import practica3.objetos.SesionEmpleados;

public final class nuevo_002dsalario_jsp extends org.apache.jasper.runtime.HttpJspBase
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
        AumentoSalarios aumento = new AumentoSalarios();
        String user = sesion.getUsername();
        int id_empleado = (int) session.getAttribute("idEmpleado");
        float salarioActual = aumento.salarioActual(id_empleado);
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "EstilosPerfiles/estilo-cuenta-supervisor.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container\"><br><br>\n");
      out.write("            <h1>Aumento salarial</h1>\n");
      out.write("            Salario Actual: ");
      out.print(salarioActual);
      out.write("\n");
      out.write("            <form action=\"ControladorOperacionesHistorial?id=");
      out.print(id_empleado);
      out.write("\" method=\"POST\">\n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group col-md-4\">\n");
      out.write("                        <input type=\"number\" id=\"salario\" name=\"salario\" class=\"form-control\" placeholder=\"Nuevo salario\" required/>                        \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <input type=\"date\" id=\"fecha\" name=\"fecha\" class=\"form-control\" required><br><br>\n");
      out.write("                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\"  name=\"accion\" value=\"Confirmar aumento\">Confirmar aumento</button>\n");
      out.write("            </form>\n");
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
