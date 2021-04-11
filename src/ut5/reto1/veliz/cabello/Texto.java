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
        System.out.println("1) Tirar de la ruleta");
        System.out.println("2) Resolver el panel");
        System.out.println("3) Comprar vocal (50€)");
    }
}
