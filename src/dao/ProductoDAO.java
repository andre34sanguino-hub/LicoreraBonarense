package dao;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Producto;
public class ProductoDAO {

    conexion con = new conexion();
    Connection cn;
public void guardarProducto(Producto p) {

    String sql = "INSERT INTO producto(nombre, categoria, precio, cantidad) VALUES (?,?,?,?)";

    try {

        cn = con.establecerConexion();
        PreparedStatement ps = cn.prepareStatement(sql);

        ps.setString(1, p.getNombre());
        ps.setString(2, p.getCategoria());
        ps.setDouble(3, p.getPrecio());
        ps.setInt(4, p.getCantidad());

        ps.executeUpdate();

        System.out.println("Producto guardado correctamente");

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

}
public Producto buscarProducto(int id) {

    String sql = "SELECT * FROM producto WHERE id = ?";

    try {
        cn = con.establecerConexion();

        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, id);

        var rs = ps.executeQuery();

        if (rs.next()) {
            Producto p = new Producto();

            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setCategoria(rs.getString("categoria"));
            p.setPrecio(rs.getDouble("precio"));
            p.setCantidad(rs.getInt("cantidad"));

            return p;
        }

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return null;
}
public void actualizarProducto(Producto p) {

    String sql = "UPDATE producto SET nombre = ?, categoria = ?, precio = ?, cantidad = ? WHERE id = ?";

    try {
        cn = con.establecerConexion();

        PreparedStatement ps = cn.prepareStatement(sql);

        ps.setString(1, p.getNombre());
        ps.setString(2, p.getCategoria());
        ps.setDouble(3, p.getPrecio());
        ps.setInt(4, p.getCantidad());
        ps.setInt(5, p.getId());

        ps.executeUpdate();

        System.out.println("Producto actualizado correctamente");

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public void eliminarProducto(int id) {

    String sql = "DELETE FROM producto WHERE id = ?";

    try {
        cn = con.establecerConexion();

        PreparedStatement ps = cn.prepareStatement(sql);

        ps.setInt(1, id);

        ps.executeUpdate();

        System.out.println("Producto eliminado correctamente");

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}