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
        <title>Estadí­sticas de Fútbol</title>
        <meta charset="UTF-8">
        <link href="./css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        
    <h1>Estadí­sticas de Jugadores de Fútbol</h1>
    
    <p><font size="7">Vote por el mejor jugador</font></p>
    
    <form action="./Futbol" method="POST">
    
        <p> Nombre del visitante:   <input type="text" size="20" name="txtNombre">   </p>
        <p> Correo electrónico:     <input type="email" size="40" name="txtMail"> </p>
        
        <%
                // Lista ordenada por votos, de mayor a menor
                ArrayList<Jugador> lista = JugadorDAO.consultarJugadores(true);
 
                for (Jugador j : lista ) {
                       out.print("<p><input type='radio' name='r1' value='");
                       out.print(j.getNombre());
                       out.print("'>");
                       out.print(j.getNombre());
                       out.print("</p>");
                    }
        %>
            
        
        
        <p> <input type="radio"  name="r1" value="Otros"> Otros:
            <input type="text" size="50" name="txtOtros"> </p>
        <p> <input type="submit" name="buttonSubmit" value="Votar"> 
            <input type="reset" name="buttonReset" value="Reset"> </p>
    </form>
    </body>
</html>



