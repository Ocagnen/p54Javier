
package daw.futbol.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author J. Carlos F. Vico <jfervic933@maralboran.es>
 */
public class JugadorDAO {
    
    private static final Connection CONEXION = Conexion.getInstance();
    
    // Este método devuelve una lista de Jugadores ordenada por Nombre
    // si creterio es FALSE o por votos si criterio es TRUE
    public static ArrayList<Jugador> consultarJugadores(boolean criterio){
        Statement st;
        ResultSet res;
        ArrayList<Jugador> lista = new ArrayList();
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = (criterio)?"select * from Jugadores order by Votos desc":"select * from Jugadores order by Nombre";

        try {
            
            // Preparamos Statement
            st = CONEXION.createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()){
                Jugador j = new Jugador();
                // Recogemos los datos del turismo, guardamos en un objeto
                j.setNombre(res.getString("Nombre"));
                j.setVotos(res.getInt("Votos"));

                //Añadimos el objeto al array
                lista.add(j);
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla Jugadores");
            System.out.println(e); 
        }

        return lista;  
    }
    
    public static int insertarJugador(String nombre){
        
        // Cadena con la consulta parametrizada
        String sql = "insert into Jugadores values (?,?)";

        PreparedStatement prest;

        try { 
            // Preparamos la inserción de datos  mediante un PreparedStatement
            prest = CONEXION.prepareStatement(sql);

            // Procedemos a indicar los valores que queremos insertar
            // Usamos los métodos setXXX(indice, valor)
            // indice indica la posicion del argumento ?, empieza en 1
            // valor es el dato que queremos insertar
            prest.setString(1, nombre);
            prest.setInt(2, 1);

            // Ejecutamos la sentencia de inserción preparada anteriormente
            int nfilas = prest.executeUpdate();
    
            // Cerramos el recurso PreparedStatement 
            prest.close();
            
           
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla Jugadores");
            System.out.println(e);
            return -1;
        }
    }
    
    public static int actualizarJugador(String nombre){
        // Cadena con la consulta 
        String sql = "update Jugadores set votos = votos+1 where Nombre like '" + nombre + "'";
        
        try {

            int nfilas;
            // Ejecutamos la sentencia de modificación
            //try-with-resources
            try (Statement prest = CONEXION.createStatement()) {
                // Ejecutamos la sentencia de modificación
                nfilas = prest.executeUpdate(sql);
            }
            
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la modificación de datos en la tabla Jugadores");
            System.out.println(e);
            return -1;
        }
    }
    
}
