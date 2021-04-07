package ut5.reto1.veliz.cabello;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * El patíbulo controla la cantidad de intentos que llevamos, así como si aún
 * quedan intentos al jugador.
 *
 * @author Agustín Crespo Valencia,
 * <a href="mailto:crespo.agustin@gmail.com">escríbeme un correo</a>
 * @version 1.0
 */
public class Patibulo {

    /**
     * Máximo de intentos disponibles en este juego
     */
    public static final int MAX_INTENTOS = 6;

    private int intentos;

    public Patibulo() {
        intentos = Patibulo.MAX_INTENTOS;
    }

    /**
     * Comprueba si el jugador tiene aún intentos o no
     *
     * @return <b>true</b> si no tiene intentos, <b>false</b> en caso contrario
     */
    public boolean isDead() {
        return (this.intentos <= 0);
    }

    /**
     * Comprueba si al jugador le quedan aún intentos para jugar
     *
     * @return <b>true</b> si no tiene intentos, <b>false</b> en caso contrario
     */
    public boolean isAlive() {
        return (this.intentos > 0);
    }

    /**
     * Resetea el patíbulo para un siguiente juego
     */
    public void reset() {
        this.intentos = Patibulo.MAX_INTENTOS;
    }

    /**
     * Recupera la cantidad de intentos totales que le quedan al jugador
     *
     * @return número de intentos que le quedan al jugador
     */
    public int getIntentos() {
        return this.intentos;
    }

    /**
     * Anota el fallo en el patíbulo y devuelve un dibujo del patíbulo
     *
     * @return String con la representación gráfica del patíbulo.
     */
    public String fails() {
        this.intentos--;

        StringBuilder sb = new StringBuilder();

        sb.append("|------ \n");
        sb.append("|     | \n");

        sb.append("|");
        if (this.intentos <= Patibulo.MAX_INTENTOS - 1) {
            sb.append("     0 ");
        }
        sb.append("\n");

        sb.append("|");
        if (this.intentos == Patibulo.MAX_INTENTOS - 2) {
            sb.append("     | ");
        } else if (this.intentos == Patibulo.MAX_INTENTOS - 3) {
            sb.append("    /| ");
        } else if (this.intentos <= Patibulo.MAX_INTENTOS - 4) {
            sb.append("    /|\\");
        }
        sb.append("\n");

        sb.append("|");
        if (this.intentos == Patibulo.MAX_INTENTOS - 5) {
            sb.append("    /  ");
        } else if (this.intentos <= Patibulo.MAX_INTENTOS - 6) {
            sb.append("    / \\");
        }
        sb.append("\n");

        sb.append("|\n");
        sb.append("|__________\n");

        return sb.toString();
    }

    // PRUEBA DE QUE EL PATÍBULO FUNCIONA
    public static void main(String[] args) {
        Patibulo p = new Patibulo();
        String s;
        do {
            s = p.fails();
            System.out.println(s);
        } while (p.isAlive());
    }
}
