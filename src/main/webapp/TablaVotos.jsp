<%-- 
    Author     : J. Carlos F. Vico <jfervic933@maralboran.es>
--%>

<%@page import="daw.futbol.modelo.JugadorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daw.futbol.modelo.Jugador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
        <link href="./css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Ranking de votos</h1>
        <h1>Hola ${sessionScope['nombreVistante']}, ha votado por ${sessionScope['jugador']} y el ránking queda así </h1>
        <table border ="1">
            
            <tr>
                <th>
                    <b>Jugador</b>
                </th>
                <th>
                    <b>Votos</b>
                </th>
            </tr>
            
            <%
                // Lista ordenada por votos, de mayor a menor
                ArrayList<Jugador> lista = JugadorDAO.consultarJugadores(true);
 
                for (Jugador j : lista ) {
                       out.print("<tr><td>");
                       out.print(j.getNombre());
                       out.print("</td><td>");
                       out.print(j.getVotos());
                       out.print("</td><tr>");
                    }
            %>
            
        </table>
            
         <button type="button" onclick="location.href = './index.jsp'">Volver</button>   
 
    </body>
</html>
