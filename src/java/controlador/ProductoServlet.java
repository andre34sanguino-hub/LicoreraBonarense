/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>Licorera Bonarense</title></head>");
    out.println("<body>");

    out.println("<h1>Licorera Bonarense</h1>");
    out.println("<h2>Módulo de productos</h2>");
    out.println("<p>El método GET funciona correctamente.</p>");

    out.println("</body>");
    out.println("</html>");
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Recibir los datos del formulario
    String nombre = request.getParameter("nombre");
    String categoria = request.getParameter("categoria");
    String precio = request.getParameter("precio");
    String cantidad = request.getParameter("cantidad");

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>Producto guardado</title></head>");
    out.println("<body>");

    out.println("<h1>Producto recibido correctamente</h1>");
    out.println("<p><strong>Nombre:</strong> " + nombre + "</p>");
    out.println("<p><strong>Categoría:</strong> " + categoria + "</p>");
    out.println("<p><strong>Precio:</strong> " + precio + "</p>");
    out.println("<p><strong>Cantidad:</strong> " + cantidad + "</p>");

    out.println("</body>");
    out.println("</html>");
}
}