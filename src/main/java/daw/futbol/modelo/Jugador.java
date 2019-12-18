package daw.futbol.modelo;

/**
 *
 * @author J. Carlos F. Vico <jfervic933@maralboran.es>
 */
public class Jugador {
    
    private String nombre;
    private int votos;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", votos=" + votos + '}';
    }

}
