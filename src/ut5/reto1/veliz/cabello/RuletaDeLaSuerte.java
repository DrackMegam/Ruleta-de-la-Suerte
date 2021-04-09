/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut5.reto1.veliz.cabello;

import java.util.Scanner;

/**
 *
 * @author DrackMegam
 */
public class RuletaDeLaSuerte {

    public static void main(String[] args) {
        Scanner usu = new Scanner(System.in);
        String respuestaUsuario;
        int numeroJugadores = 0;
        boolean verificarJugadores;

        // Inicio del juego.
        System.out.println("BIENVENIDA");

        do {
            verificarJugadores = false;
            System.out.printf("Nº jugadores: ");
            respuestaUsuario = usu.nextLine();

            // Verifico la respuesta del usuario.
            if (respuestaUsuario.equals("2")
                    || respuestaUsuario.equals("3")
                    || respuestaUsuario.equals("4")
                    || respuestaUsuario.equals("5")
                    || respuestaUsuario.equals("6")) {
                verificarJugadores = true;
                System.out.println("VERIFICADO");
            }
        } while (!verificarJugadores);

        // Una vez que conozco el nº jugadores, lo paso a int.
        numeroJugadores = Integer.parseInt(respuestaUsuario);
        
        // Comienzo a crear jugadores.
        
        Jugador player1 = new Jugador("player1",2000,10);
        

    }
}
