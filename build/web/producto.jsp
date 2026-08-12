<%-- 
    Document   : producto.jsp
    Created on : 11/08/2026, 3:35:29 p. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>Licorera Bonarense</h1>

    <h2>Módulo de productos</h2>

    <p>Registro de productos mediante JSP y Servlet.</p>

    <form action="ProductoServlet" method="post">

        <label>Nombre del producto:</label><br>
        <input type="text" name="nombre" required><br><br>

        <label>Categoría:</label><br>
        <input type="text" name="categoria" required><br><br>

        <label>Precio:</label><br>
        <input type="number" name="precio" required><br><br>

        <label>Cantidad:</label><br>
        <input type="number" name="cantidad" required><br><br>

        <button type="submit">Guardar producto</button>

    </form>

</body>
</html>
