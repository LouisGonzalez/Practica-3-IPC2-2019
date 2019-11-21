package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import practica3.objetos.SesionEmpleados;

public final class nuevo_002dmedicamento_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("                alert('Medicamento creado con exito');\n");
      out.write("            }\n");
      out.write("        </script>    \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "EstilosPerfiles/estilo-cuenta-farmaceutico.jsp", out, false);
      out.write("\n");
      out.write("        <div class='container'><br><br>\n");
      out.write("            <h1>Formulario para la creacion de un nuevo medicamento</h1><br>\n");
      out.write("            <form action=\"ControladorMedicamentos\" method=\"POST\">\n");
      out.write("                <input id=\"nombre\" name=\"nombre\" class=\"form-control\" placeholder=\"Nombre del medicamento\" required><br>\n");
      out.write("                <textarea name=\"descripcion\" class=\"form-control\" rows=\"5\" cols=\"85\" spellcheck=\"true\" placeholder=\"Agrega una descripcion del medicamento\"></textarea><br>\n");
      out.write("                <input type=\"number\" name=\"costoUnitario\" step='.01' class=\"form-control\" placeholder=\"Costo unitario\" required>\n");
      out.write("                <input type=\"number\" name=\"precioVenta\" step='.01' class=\"form-control\" placeholder=\"Precio de venta\" required>\n");
      out.write("                <input type='number' name='existencia' class='form-control' placeholder=\"Cant existencia\" required>\n");
      out.write("                <input type='number' name='limiteExistencia' class='form-control' placeholder='Limite de existencia' required><br>\n");
      out.write("                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\" name=\"accion\" onclick=\"mensaje()\" value=\"Guardar medicamento\">Guardar medicamento</button>\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>    \n");
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
