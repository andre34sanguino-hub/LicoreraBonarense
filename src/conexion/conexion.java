package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    Connection conectar = null;

    String usuario = "root";
    String contraseña = "Sena2026#";
    String bd = "licorera";
    String ip = "127.0.0.1";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {

        try {
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return conectar;
    }
}