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
public class Jugador {

    private String nombreJugador;
    private final static String NOMBRE_PREDETERMINADO="jugador"; 
    private int dinero;
    private final static int DINERO_PREDETERMINADO=2000; 
    private int bote;
    private final static int BOTE_PREDETERMINADO=0; 

    public Jugador(String nombreJugador, int dinero, int bote) {
        this.nombreJugador = nombreJugador;
        this.dinero = dinero;
        this.bote = bote;
    }
    public Jugador(){
        this.nombreJugador=NOMBRE_PREDETERMINADO;
        this.dinero=DINERO_PREDETERMINADO;
        this.bote=BOTE_PREDETERMINADO;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getBote() {
        return bote;
    }

    public void setBote(int bote) {
        this.bote = bote;
    }

}
