/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut5.reto1.veliz.cabello;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author DrackMegam
 */
public class Texto {

    public static void comenzarTurno(Jugador jugador) {
        System.out.println();
        System.out.println("¡Es el turno de " + jugador.getNombreJugador() + "!");
    }

    public static void opcionesTurno() {
        
        System.out.println("1) Tirar de la ruleta");
        System.out.println("2) Resolver el panel");
        System.out.println("3) Comprar vocal (50€)");
    }

    public static void tirarRuleta(Jugador jugador) {
        System.out.println();
        System.out.println("¡Es hora de tirar de la ruleta, " + jugador.getNombreJugador() + "!");
        System.out.println("[ENTER]");
    }

    public static void resolverPanel(Jugador jugador, ArrayList panel) {
        System.out.println(jugador.getNombreJugador() + " va a resolver el panel.");
        System.out.println("¡Si aciertas, conseguirás el bote que tienes acumulado!");
        System.out.println();
        for (int i = 0; i < panel.size(); i++) {
            System.out.print(panel.get(i));
        }
        System.out.println();
    }

    public static void mostrarPanel(ArrayList panel) {
        for (int i = 0; i < panel.size(); i++) {
            System.out.print(panel.get(i));
        }
        System.out.println();
    }

    public static void comprarVocal(Jugador jugador) {
        System.out.println();
        System.out.println(jugador.getNombreJugador() + " va a comprar una vocal.");
        System.out.println("Bote actual: " + jugador.getBote());
        System.out.println("Bote tras compra: " + (jugador.getBote() - 50));
    }

    public static void mostrarPista(String pistasElegidas) {
        System.out.println();
        System.out.printf("PISTA: ");
        System.out.print(pistasElegidas);
        System.out.println();
    }

    public static void fallarPanel() {
        System.out.println();
        System.out.println("Has fallado y has perdido el turno...");
        System.out.println();
    }

    public static void acertarPanel() {
        System.out.println();
        System.out.println("¡Has resuelto el panel!");
        System.out.println();
    }
    
    public static void presentacion(){
        System.out.println("¡Bienvenido a la Ruleta de la Suerte!");
        System.out.println();
        System.out.println("Imagino que todos sabréis jugar.");
        System.out.println("Así que vayammos directos al concurso.");
        System.out.println();
        System.out.println("Antes de jugar, veamos como será la partida.");
        System.out.println();
    }
}