/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut5.reto1.veliz.cabello;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

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
            }
        } while (!verificarJugadores);

        // Una vez que conozco el nº jugadores, lo paso a int.
        numeroJugadores = Integer.parseInt(respuestaUsuario);

        // Comienzo a crear jugadores, empezando por el nombre.
        Queue<String> nombresJugadores = new LinkedTransferQueue<>();
        for (int i = 0; i < numeroJugadores; i++) {
            respuestaUsuario=null;
            // Pregunto el nombre del jugador.
            do {
                // i+1 para mostrar la "posición real".
                System.out.printf("Nombre jugador "+(i+1)+": ");
                respuestaUsuario=usu.nextLine();
            } while (respuestaUsuario == null 
                    || respuestaUsuario.trim().length() == 0);
            // Añado el nombre correcto a la cola.
            nombresJugadores.add(respuestaUsuario);
            
        }
        
        // Creo los objetos del jugador.
        List<Jugador> jugadores = new ArrayList<>();
        for(int i =0;i<numeroJugadores;i++){
            // Uso la cola donde antes he guardado los nombres.
            Jugador jugador = new Jugador(nombresJugadores.poll(),2000,0);
            jugadores.add(jugador);
        }
        
//        // MOSTRAR TODOS LOS NOMBRES.
//        System.out.println();
//        System.out.println("Repasemos los nombres: ");
//        for(int i =0;i<numeroJugadores;i++){
//            System.out.println("Jugador nº"+(i+1)+": "+jugadores.get(i).getNombreJugador());
//        }
        
        
        
        // Aquí comienza el juego.
        
        // Turno del jugador que empieza, y para ver como avanzan los turnos..
        int turnoJugador=(int) (Math.random()*numeroJugadores);
        
        System.out.println("Le toca al jugador...");
        System.out.println("¡"+jugadores.get(turnoJugador).getNombreJugador()+"!");
        
        // TODO elegir paneles y crear dichos objetos.
        
        // TODO empezar turno de jugador y funcionalidad del juego.
        boolean juegoVivo=true;
        do{
            // Invoco el texto personalizado.
            Texto.comenzarTurno(jugadores.get(turnoJugador));
        }while(juegoVivo);
        
            // TODO tirar de la ruleta y decir consonante.
            
                /**
                 * Probabilidades de la ruleta.
                 * 0 - 7.5      --> Quiebra
                 * 7.6 - 15     --> Perder el turno
                 * 15.1 - 25    --> 0€
                 * 25.1 - 50    --> 25€
                 * 50.1 - 65    --> 50€
                 * 65.1 - 75    --> 75€
                 * 75.1 - 80    --> 100€
                 * 80.1 - 83.75 --> 150€
                 * 83.76 - 87.5 --> 200
                 * 87.6 - 95    --> Vuelve a tirar
                 * 95.1 - 100   --> Vocal gratis, vuelve a tirar.
                 */
            
            // TODO Resolver el panel y cagar los demáses boteses.
            
            // TODO Comprar una vocal, comprobando bote y demáses.
    }
    
    public static int avanzarTurno(int turnoActual,int turnoMaximo){
        int turno=0;
        
        return turno;
    }
}
