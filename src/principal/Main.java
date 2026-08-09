package principal;

import dao.ProductoDAO;
import modelo.Producto;

public class Main {

    public static void main(String[] args) {

        Producto p = new Producto();

        p.setNombre("Cerveza Aguila");
        p.setCategoria("Cerveza");
        p.setPrecio(3500);
        p.setCantidad(20);

        ProductoDAO dao = new ProductoDAO();
        dao.guardarProducto(p);

    }

}