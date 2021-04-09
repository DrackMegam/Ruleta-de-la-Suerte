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
    private int dinero;
    private int bote;

    public Jugador(String nombreJugador, int dinero, int bote) {
        this.nombreJugador = nombreJugador;
        this.dinero = dinero;
        this.bote = bote;
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
