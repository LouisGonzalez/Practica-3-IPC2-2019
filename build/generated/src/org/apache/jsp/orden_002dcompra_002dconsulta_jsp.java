package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import practica3.objetos.Facturas;
import practica3.funcionesMedico.GeneracionFactura;
import java.sql.*;
import practica3.conexion.Conexion;
import practica3.objetos.SesionEmpleados;

public final class orden_002dcompra_002dconsulta_jsp extends org.apache.jasper.runtime.HttpJspBase
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

            GeneracionFactura factura = new GeneracionFactura();
            SesionEmpleados sesion = (SesionEmpleados) session.getAttribute("usuario");
            String user = sesion.getUsername();
            int id_empleado = sesion.getId();
            boolean verificador = (boolean) session.getAttribute("verificador");
            int x = (int) session.getAttribute("numFilas");
        
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "EstilosPerfiles/estilo-cuenta-medico.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container\"><br><br>\n");
      out.write("            <h1>Factura</h1>\n");
      out.write("            <form action=\"ControladorFacturas\" method=\"POST\">\n");
      out.write("                <input type=\"number\" name=\"numeroFilas\" class=\"form-control\" placeholder=\"Numero de filas\" required><br>\n");
      out.write("                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\" name=\"accion\" value=\"Numero de filas\">Numero de filas</button>            \n");
      out.write("            </form><br><br>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <form action=\"ControladorFacturas?idMedico=");
      out.print(id_empleado);
      out.write("\" method=\"POST\">\n");
      out.write("                \n");
      out.write("                ");
if (verificador == false){
      out.write("\n");
      out.write("                    <input name=\"nombres\" class=\"form-control\" placeholder=\"Nombres\" required>\n");
      out.write("                    <input name=\"apellidos\" class=\"form-control\" placeholder=\"Apellidos\" required>\n");
      out.write("                    <input name=\"ciudad\" class=\"form-control\" placeholder=\"Ciudad\" required>\n");
      out.write("                    <input type=\"number\" name=\"nit\" class=\"form-control\" placeholder=\"Nit\" required>\n");
      out.write("                    <input type=\"date\" name=\"fecha\" class=\"form-control\" required><br>\n");
      out.write("                ");
} else {
                Facturas datos = (Facturas) session.getAttribute("datosPersonales");
                
      out.write("\n");
      out.write("                    <input name=\"nombres\" class=\"form-control\" value=\"");
      out.print(datos.getNombres());
      out.write("\" required>\n");
      out.write("                    <input name=\"apellidos\" class=\"form-control\" value=\"");
      out.print(datos.getApellidos());
      out.write("\" required>\n");
      out.write("                    <input name=\"ciudad\" class=\"form-control\" value=\"");
      out.print(datos.getCiudad());
      out.write("\" required>\n");
      out.write("                    <input type=\"number\" name=\"nit\" class=\"form-control\" value=\"");
      out.print(datos.getNit());
      out.write("\">\n");
      out.write("                    <input type=\"date\" name=\"fecha\" class=\"form-control\" value=\"");
      out.print(datos.getFecha_factura());
      out.write("\"><br>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("                <table class=\"table\" WIDTH=\"30%\">\n");
      out.write("                    <thead class=\"thead-dark\">\n");
      out.write("                        <tr>\n");
      out.write("                            <th scope=\"col\">#</th>\n");
      out.write("                            <th scope=\"col\">Cant.</th>\n");
      out.write("                            <th scope=\"col\">Tipo Medicina</th>\n");
      out.write("                            <th scope=\"col\">Medicina</th>\n");
      out.write("                            <th scope=\"col\">Total</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("                        ");

                            int y = 0;
                            float cant = 0;
                            for (int i = 0; i < x; i++) {
                                y++;
                        
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td scope=\"row\" WIDTH=\"100\">");
      out.print(y);
      out.write("</td>\n");
      out.write("                            <td WIDTH=\"100\">                       \n");
      out.write("                                ");
if (verificador == false) {
      out.write("\n");
      out.write("                                <input type=\"number\" name=\"cantMedicina");
      out.print(i);
      out.write("\" class=\"form-control\" placeholder=\"Cant\" required>\n");
      out.write("                                ");
} else {
      out.write("\n");
      out.write("                                <input type=\"number\" name=\"cantMedicina");
      out.print(i);
      out.write("\" class=\"form-control\" placeholder=\"Cant\" value=\"");
      out.print((int) session.getAttribute("cantMedicamento" + i));
      out.write("\" required>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </td>\n");
      out.write("                            <td WIDTH=\"200\">\n");
      out.write("                                <select name=\"medicinas");
      out.print(i);
      out.write("\" class=\"form-control\">\n");
      out.write("                                    ");

                                        Conexion login = new Conexion();
                                        Connection cn = login.getConnection();
                                        String consulta = "SELECT * FROM Medicamentos ORDER BY id";
                                        PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                        ResultSet result = declaracionConsulta.executeQuery();
                                        while (result.next()) {
                                            
      out.write("<option value=\"");
      out.print(result.getString("nombre"));
      out.write('"');
      out.write('>');
      out.print(result.getString("nombre"));
      out.write("</option>>");

                                        }
                                    
      out.write("\n");
      out.write("                               </select>                   \n");
      out.write("                            </td>\n");
      out.write("                            \n");
      out.write("                            \n");
      out.write("\n");
      out.write("                            ");
if (verificador == false) {
      out.write("\n");
      out.write("                                <td><input name=\"nombreMedicamento");
      out.print(i);
      out.write("\" class=\"form-control\"></td>\n");
      out.write("                                <td><input type=\"number\" name=\"totalUnitario");
      out.print(i);
      out.write("\" class=\"form-control\"></td>\n");
      out.write("                            ");
} else {
      out.write("\n");
      out.write("                                <td><input name=\"nombreMedicina");
      out.print(i);
      out.write("\" class=\"form-control\" value=\"");
      out.print((String) session.getAttribute("nombreMedicamento" + i));
      out.write("\"></td>    \n");
      out.write("                                <td><input type=\"number\" name=\"totalUnitario");
      out.print(i);
      out.write("\" class=\"form-control\" value=\"");
      out.print((float) session.getAttribute("totalUnitario" + i));
      out.write("\"></td>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        </tr>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                </table>\n");
      out.write("                <table> \n");
      out.write("                    <tr>\n");
      out.write("                        <td>\n");
      out.write("                            <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\" name=\"accion\" value=\"Calcular totales\">Calcular totales</button>            \n");
      out.write("                        </td>\n");
      out.write("                        ");
if(verificador == false){
      out.write("\n");
      out.write("                        <td><input type=\"number\" name=\"totalFinal\" class=\"form-control\"></td>\n");
      out.write("                        ");
} else {
                        Facturas datos = (Facturas) session.getAttribute("datosPersonales");
      out.write("               \n");
      out.write("                            <td><input type=\"number\" name=\"totalFinal\" class=\"form-control\" value=\"");
      out.print(datos.getTotal());
      out.write("\"></td>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </tr>\n");
      out.write("                </table><br><br>\n");
      out.write("                <button class=\"btn btn-lg btn-primary btn-block text-uppercase\" type=\"submit\" name=\"accion\" value=\"Crear factura\">Crear factura</button>                                            \n");
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
