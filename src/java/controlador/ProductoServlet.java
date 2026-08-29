/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de gestionar los productos
 * del sistema Licorera Bonarense.
 * Utiliza métodos GET y POST.
 *
 * @author Andrea Rojas
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    private static List<Producto> productos = new ArrayList<>();

public static class Producto {
    String nombre;
    String categoria;
    String precio;
    String cantidad;

    public Producto(String nombre, String categoria, String precio, String cantidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
    // Método GET para mostrar la página de productos
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
// Método POST para procesar la información del formulario
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    // Recibir los datos del formulario
    String nombre = request.getParameter("nombre");
    String categoria = request.getParameter("categoria");
    String precio = request.getParameter("precio");
    String cantidad = request.getParameter("cantidad");
    String accion = request.getParameter("accion");

if ("consultar".equals(accion)) {

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>Consultar productos</title></head>");
    out.println("<body>");

    out.println("<h1>Productos registrados</h1>");

    for (Producto p : productos) {
        out.println("<p>");
        out.println("<strong>Nombre:</strong> " + p.nombre + "<br>");
        out.println("<strong>Categoría:</strong> " + p.categoria + "<br>");
        out.println("<strong>Precio:</strong> " + p.precio + "<br>");
        out.println("<strong>Cantidad:</strong> " + p.cantidad + "<br>");
        out.println("</p>");
        out.println("<hr>");
    }

    out.println("</body>");
    out.println("</html>");

    return;
}
if ("modificar".equals(accion)) {

    for (Producto p : productos) {

        if (p.nombre.equals(nombre)) {
            p.categoria = categoria;
            p.precio = precio;
            p.cantidad = cantidad;
        }
    }

   response.setContentType("text/html;charset=UTF-8");

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head><title>Producto modificado</title></head>");
out.println("<body>");

out.println("<h1>Producto modificado correctamente</h1>");
out.println("<p><strong>Nombre:</strong> " + nombre + "</p>");
out.println("<p><strong>Categoría:</strong> " + categoria + "</p>");
out.println("<p><strong>Precio:</strong> " + precio + "</p>");
out.println("<p><strong>Cantidad:</strong> " + cantidad + "</p>");

out.println("</body>");
out.println("</html>");

return;
}
if ("eliminar".equals(accion)) {

    productos.removeIf(p -> p.nombre.equals(nombre));

    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>Producto eliminado</title></head>");
    out.println("<body>");

    out.println("<h1>Producto eliminado correctamente</h1>");
    out.println("<p><strong>Nombre:</strong> " + nombre + "</p>");

    out.println("</body>");
    out.println("</html>");

    return;
}
    productos.add(new Producto(nombre, categoria, precio, cantidad));

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