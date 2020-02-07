package daw.futbol.modelo;

/**
 *
 * @author J. Carlos F. Vico <jfervic933@maralboran.es>
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static final String SERVIDOR = "jdbc:mysql://localhost/";
    private static final String NOMBRE_BASE_DATOS = "BDJugadores";
    private static final String USER = "usuario";
    private static final String PASS = "12345";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection instancia = null;

    private Conexion() {

    }

    // Método de clase para acceder a la instancia del objeto Connection
    public static Connection getInstance() {
        // Si el objeto Connection no está creado, se crea
        if (instancia == null) {
            try {
                Class.forName(DRIVER);
                // Se crea el objeto Connection	
                instancia = DriverManager.getConnection(SERVIDOR + NOMBRE_BASE_DATOS, USER, PASS);

                System.out.println("Conexión realizada con éxito.");

            } catch (SQLException e) {
                // Error en la conexión
                System.out.println("Conexión fallida: " + e.getMessage());
            
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
         return instancia;
    }
}

