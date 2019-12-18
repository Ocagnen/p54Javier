/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.futbol.controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daw.futbol.modelo.Jugador;
import daw.futbol.modelo.JugadorDAO;

/**
 *
 * @author carlos
 */
public class Futbol extends HttpServlet {

    // El siguiente método procesa la petición para métodos GET y POST
    // Tiene dos parámetros:
    //  -   request objeto tipo HTTPServletRequest con info de la 
    //      petición del cliente
    //  -   response objeto tipo HTTPServletResponse con info de la 
    //      respuesta al cliente
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Se establece el tipo de contenido a enviar en la respuesta
        response.setContentType("text/html;charset=UTF-8");

        // Obtengo la sesion de la petición HTTP, si existe. 
        // Con true, si no está creada se crea
        HttpSession sesion = request.getSession(true);

        // Guardo el nombre del visitante en un String
        String nombreVistante = request.getParameter("txtNombre");
        // Asigno ese nombre del visitante al atributo de la sesión y así lo puedo usar en la vista
        sesion.setAttribute("nombreVistante", nombreVistante);

        // Obtengo al jugador votado
        String jugadorVotado = request.getParameter("r1");
        if (jugadorVotado != null) {
            // Si el visitante ha elegido "otros", hay que obtener el valor de la caja de texto
            if (jugadorVotado.equals("Otros")) {
                jugadorVotado = request.getParameter("txtOtros");
            }

            // Asigno ese nombre del jugador al atributo de la sesión y así lo puedo usar en la vista
            sesion.setAttribute("jugador", jugadorVotado);

            // Obtengo la lista de jugadores que hay en la base de datos
            // ordenada por nombre
            ArrayList<Jugador> lista = JugadorDAO.consultarJugadores(false);

            // En este punto miramos si el jugador existe en la lista o no
            // Si existe, habría que sumarle uno a los votos - UPDATE
            // Si no existe, hay que insertarlo poniendo a 1 sus votos - INSERT
            if (buscarJugador(lista, jugadorVotado)) {
                // Actualizar votos
                JugadorDAO.actualizarJugador(jugadorVotado);
            } else {
                // Como no existe el jugador en la base de datos, hay que insertarlo con sus votos a 1
                JugadorDAO.insertarJugador(jugadorVotado);
            }

            // Obtengo la lista actualizada de jugadores, ordenada por votos
            lista = JugadorDAO.consultarJugadores(true);

            // Expresión lambda para imprimir los elementos de la lista
            lista.forEach(System.out::println);

            // Una vez realizada la operación, redirigimos a la vista
            //response.sendRedirect(response.encodeRedirectURL("TablaVotos.jsp"));
            response.sendRedirect("TablaVotos.jsp");

        }
        else{
            response.sendRedirect("./index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Método que hace búsqueda de un Jugador por su nombre
    // dentro de la lista de Jugadores
    private static boolean buscarJugador(ArrayList<Jugador> lista, String nombre) {
        // Ejemplo de uso de expresiones lambda y API Stream
        return lista.stream().anyMatch((jugador) -> (jugador.getNombre().equals(nombre)));
    }

}
