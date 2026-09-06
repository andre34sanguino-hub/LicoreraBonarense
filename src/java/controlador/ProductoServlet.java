package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * API REST para gestionar los productos de la Licorera Bonarense.
 *
 * Servicios disponibles:
 * GET    - Consultar productos
 * POST   - Registrar producto
 * PUT    - Modificar producto
 * DELETE - Eliminar producto
 *
 * @author Andrea Rojas
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    // Lista temporal donde se almacenan los productos.
    private static final List<Producto> productos = new ArrayList<>();

    /**
     * Clase que representa un producto.
     */
    public static class Producto {

        String nombre;
        String categoria;
        String precio;
        String cantidad;

        public Producto(String nombre, String categoria,
                String precio, String cantidad) {

            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
            this.cantidad = cantidad;
        }
    }

    /**
     * Método GET.
     * Consulta todos los productos registrados.
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("{");
        out.println("  \"servicio\": \"Consulta de productos\",");
        out.println("  \"cantidad\": " + productos.size() + ",");

        out.println("  \"productos\": [");

        for (int i = 0; i < productos.size(); i++) {

            Producto p = productos.get(i);

            out.println("    {");
            out.println("      \"nombre\": \"" + escapar(p.nombre) + "\",");
            out.println("      \"categoria\": \"" + escapar(p.categoria) + "\",");
            out.println("      \"precio\": \"" + escapar(p.precio) + "\",");
            out.println("      \"cantidad\": \"" + escapar(p.cantidad) + "\"");
            out.println("    }" + (i < productos.size() - 1 ? "," : ""));
        }

        out.println("  ]");
        out.println("}");
    }

    /**
     * Método POST.
     * Registra un nuevo producto.
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String categoria = request.getParameter("categoria");
        String precio = request.getParameter("precio");
        String cantidad = request.getParameter("cantidad");

        // Validación de campos obligatorios.
        if (campoVacio(nombre) || campoVacio(categoria)
                || campoVacio(precio) || campoVacio(cantidad)) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.println("{");
            out.println("  \"error\": \"Todos los campos son obligatorios\"");
            out.println("}");

            return;
        }

        // Validación del precio y cantidad.
        if (!numeroValido(precio) || !numeroValido(cantidad)) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.println("{");
            out.println("  \"error\": \"El precio y la cantidad deben ser números válidos\"");
            out.println("}");

            return;
        }

        productos.add(new Producto(
                nombre, categoria, precio, cantidad));

        response.setStatus(HttpServletResponse.SC_CREATED);

        out.println("{");
        out.println("  \"mensaje\": \"Producto registrado correctamente\",");
        out.println("  \"producto\": \"" + escapar(nombre) + "\"");
        out.println("}");
    }

    /**
     * Método PUT.
     * Modifica un producto existente.
     *
     * Los datos pueden enviarse como parámetros:
     * nombre, categoria, precio y cantidad.
     */
    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String cuerpo = leerCuerpo(request);

        String nombre = obtenerParametro(request, cuerpo, "nombre");
        String categoria = obtenerParametro(request, cuerpo, "categoria");
        String precio = obtenerParametro(request, cuerpo, "precio");
        String cantidad = obtenerParametro(request, cuerpo, "cantidad");

        // Validación de datos obligatorios.
        if (campoVacio(nombre) || campoVacio(categoria)
                || campoVacio(precio) || campoVacio(cantidad)) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.println("{");
            out.println("  \"error\": \"Todos los campos son obligatorios\"");
            out.println("}");

            return;
        }

        // Validación numérica.
        if (!numeroValido(precio) || !numeroValido(cantidad)) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.println("{");
            out.println("  \"error\": \"El precio y la cantidad deben ser números válidos\"");
            out.println("}");

            return;
        }

        boolean encontrado = false;

        for (Producto p : productos) {

            if (p.nombre.equalsIgnoreCase(nombre)) {

                p.categoria = categoria;
                p.precio = precio;
                p.cantidad = cantidad;

                encontrado = true;
                break;
            }
        }

        if (encontrado) {

            response.setStatus(HttpServletResponse.SC_OK);

            out.println("{");
            out.println("  \"mensaje\": \"Producto modificado correctamente\",");
            out.println("  \"producto\": \"" + escapar(nombre) + "\"");
            out.println("}");

        } else {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            out.println("{");
            out.println("  \"error\": \"Producto no encontrado\"");
            out.println("}");
        }
    }

    /**
     * Método DELETE.
     * Elimina un producto existente.
     */
    @Override
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String cuerpo = leerCuerpo(request);

        String nombre = obtenerParametro(request, cuerpo, "nombre");

        if (campoVacio(nombre)) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            out.println("{");
            out.println("  \"error\": \"Debe indicar el nombre del producto\"");
            out.println("}");

            return;
        }

        boolean eliminado = productos.removeIf(
                p -> p.nombre.equalsIgnoreCase(nombre));

        if (eliminado) {

            response.setStatus(HttpServletResponse.SC_OK);

            out.println("{");
            out.println("  \"mensaje\": \"Producto eliminado correctamente\",");
            out.println("  \"producto\": \"" + escapar(nombre) + "\"");
            out.println("}");

        } else {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            out.println("{");
            out.println("  \"error\": \"Producto no encontrado\"");
            out.println("}");
        }
    }

    /**
     * Verifica si un campo está vacío.
     */
    private boolean campoVacio(String valor) {

        return valor == null || valor.trim().isEmpty();
    }

    /**
     * Verifica que un valor sea numérico.
     */
    private boolean numeroValido(String valor) {

        try {

            double numero = Double.parseDouble(valor);

            return numero >= 0;

        } catch (NumberFormatException e) {

            return false;
        }
    }

    /**
     * Lee el cuerpo enviado en una petición PUT o DELETE.
     */
    private String leerCuerpo(HttpServletRequest request)
            throws IOException {

        StringBuilder cuerpo = new StringBuilder();

        BufferedReader reader = request.getReader();

        String linea;

        while ((linea = reader.readLine()) != null) {

            cuerpo.append(linea);
        }

        return cuerpo.toString();
    }

    /**
     * Obtiene un parámetro desde la petición o desde
     * el cuerpo cuando se utiliza PUT o DELETE.
     */
    private String obtenerParametro(HttpServletRequest request,
            String cuerpo, String nombreParametro) {

        String valor = request.getParameter(nombreParametro);

        if (valor != null) {
            return valor;
        }

        if (cuerpo == null || cuerpo.isEmpty()) {
            return null;
        }

        String[] parametros = cuerpo.split("&");

        for (String parametro : parametros) {

            String[] partes = parametro.split("=", 2);

            if (partes.length == 2
                    && partes[0].equals(nombreParametro)) {

                return URLDecoder.decode(
                        partes[1], StandardCharsets.UTF_8);
            }
        }

        return null;
    }

    /**
     * Evita problemas con caracteres especiales
     * al generar la respuesta JSON.
     */
    private String escapar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}