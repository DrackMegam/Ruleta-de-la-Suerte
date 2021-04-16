/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut5.reto1.veliz.cabello;

/**
 *
 * @author DrackMegam
 */
public class Texto {

        public static void comenzarTurno(Jugador jugador){
        System.out.println("¡Es el turno de "+jugador.getNombreJugador()+"!");
    }
    
    public static void opcionesTurno(){
        System.out.println("1) Tirar de la ruleta");
        System.out.println("2) Resolver el panel");
        System.out.println("3) Comprar vocal (50€)");
    }
    
    public static void tirarRuleta(Jugador jugador){
        System.out.println("¡Es hora de tirar de la ruleta, "+jugador.getNombreJugador()+"!");
        System.out.println("[ENTER]");
    }
    
    public static void resolverPanel(Jugador jugador){
        System.out.println(jugador.getNombreJugador()+" va a resolver el pane.");
        System.out.println("Si acierta, conseguirá el bote, ¡y el de sus oponentes"
                + " pasará a ser 0!");
    }
    
    public static void comprarVocal(Jugador jugador){
         System.out.println(jugador.getNombreJugador()+" va a comprar una vocal.");
         System.out.println("Bote actual: "+jugador.getBote());
         System.out.println("Bote tras compra: "+(jugador.getBote()-50));
    }
}
