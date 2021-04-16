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

    // Variables para controlar el curso del juego.
    static boolean juegoVivo = true;
    static boolean turnoJugadorVivo = true;

    public static void main(String[] args) {
        Scanner usu = new Scanner(System.in);
        String respuestaUsuario;
        int numeroJugadores = 0;
        // Para verificar el nº de jugadores y la acción del turno de estos.
        boolean verificarAccion;
        // Paneles
        String[] paneles = new String[6];
        String[] pistas = new String[6];
        buildPaneles(paneles);
        buildPustas(pistas);
        int panelAleatorio = (int) (Math.random() * 6);

        // Inicio del juego.
        System.out.println("BIENVENIDA");

        do {
            verificarAccion = false;
            System.out.printf("Nº jugadores: ");
            respuestaUsuario = usu.nextLine();

            // Verifico la respuesta del usuario.
            if (respuestaUsuario.equals("2")
                    || respuestaUsuario.equals("3")
                    || respuestaUsuario.equals("4")
                    || respuestaUsuario.equals("5")
                    || respuestaUsuario.equals("6")) {
                verificarAccion = true;
            }
        } while (!verificarAccion);

        // Una vez que conozco el nº jugadores, lo paso a int.
        numeroJugadores = Integer.parseInt(respuestaUsuario);

        // Comienzo a crear jugadores, empezando por el nombre.
        Queue<String> nombresJugadores = new LinkedTransferQueue<>();
        for (int i = 0; i < numeroJugadores; i++) {
            respuestaUsuario = null;
            // Pregunto el nombre del jugador.
            do {
                // i+1 para mostrar la "posición real".
                System.out.printf("Nombre jugador " + (i + 1) + ": ");
                respuestaUsuario = usu.nextLine();
            } while (respuestaUsuario == null
                    || respuestaUsuario.trim().length() == 0);
            // Añado el nombre correcto a la cola.
            nombresJugadores.add(respuestaUsuario);

        }

        // Creo los objetos del jugador.
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < numeroJugadores; i++) {
            // Uso la cola donde antes he guardado los nombres.
            Jugador jugador = new Jugador(nombresJugadores.poll(), 2000, 0);
            jugadores.add(jugador);
        }

        // Aquí comienza el juego.
        // Turno del jugador que empieza, y para ver como avanzan los turnos..
        int turnoJugador = (int) (Math.random() * numeroJugadores);

        System.out.println("Le toca al jugador...");
        System.out.println("¡" + jugadores.get(turnoJugador).getNombreJugador() + "!");

        // TODO elegir paneles y crear dichos objetos.
        // TODO empezar turno de jugador y funcionalidad del juego.
        respuestaUsuario = null;
        int accionJugador;
        do { // Bucle de Juego
            do { // Bucle de Turno
                turnoJugadorVivo = true;
                verificarAccion = false;
                // Invoco el texto personalizado.
                Texto.comenzarTurno(jugadores.get(turnoJugador));

                // Le obligo a tirar de la ruleta.
                Texto.tirarRuleta(jugadores.get(turnoJugador));
                tirarRuleta(jugadores.get(turnoJugador), usu);

                // Si no ha perdido el turno en la ruleta...
                if (turnoJugadorVivo) {
                    Texto.opcionesTurno();
                    do {
                        System.out.printf("||> ");
                        respuestaUsuario = usu.nextLine();
                        // Verifico que haya escrito una opción correcta.
                        if (respuestaUsuario.equals("1") || respuestaUsuario.equals("2") || respuestaUsuario.equals("3")) {
                            verificarAccion = true;
                        }
                    } while (!verificarAccion);

                    // Registro la acción y la ejecuto.
                    accionJugador = Integer.parseInt(respuestaUsuario);
                    switch (accionJugador) {
                        case 1:
                            Texto.tirarRuleta(jugadores.get(turnoJugador));
                            tirarRuleta(jugadores.get(turnoJugador), usu);
                            break;
                        case 2:
                            Texto.resolverPanel(jugadores.get(turnoJugador));
                            // TODO Resolver el panel y cagar los demáses boteses.
                            // Aquí hay que meter partes del ahorcado.
                            resolverPanel(jugadores.get(turnoJugador), usu);
                            // Acaba el turno.
                            turnoJugadorVivo = false;
                            break;
                        case 3:
                            // TODO Antes de comprar una vocal, hay que ver si
                            // aún quedan vocales o si tiene más de 50 pavos.
                            // en un IF, claro está
                            Texto.comenzarTurno(jugadores.get(turnoJugador));
                            // TODO Comprar una vocal, comprobando bote y demáses.
                            comprarVocal(jugadores.get(turnoJugador), usu);
                            break;
                    }
                }

            } while (turnoJugadorVivo);
            turnoJugador = avanzarTurno(turnoJugador, numeroJugadores - 1);
        } while (juegoVivo);
    }

    /**
     * Avanza el turno, comprobando que no se salga del Nº de jugadores. Si se
     * da dicho caso, le vuelve a tocar al 1er jugador.
     *
     * @param turnoActual Turno actual de la partida.
     * @param turnoMaximo Turno máximo, o nº de jugadores.
     * @return Turno actualizado.
     */
    public static int avanzarTurno(int turnoActual, int turnoMaximo) {
        int turno = turnoActual;
        turno++;
        if (turno > turnoMaximo) {
            turno = 0;
        }
        return turno;
    }

    /**
     * Muestra el estado de la partida. USO: estadoPartida(jugadores,
     * numeroJugadores);
     *
     * @param jugadores Jugadores actuales de la partida.
     * @param numeroJugadores Nº de jugadores actuales.
     */
    public static void estadoPartida(List<Jugador> jugadores, int numeroJugadores) {
        System.out.println();
        System.out.println("Repasemos como va la partida. ");
        System.out.println();
        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("Jugador nº" + (i + 1) + ": " + jugadores.get(i).getNombreJugador());
            System.out.println("--> Dinero: " + jugadores.get(i).getDinero());
            System.out.println("--> Bote: " + jugadores.get(i).getBote());
            System.out.println();
        }
    }

    /**
     * Método para tirar de la ruleta.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner para pulsar "enter" y tirar.
     */
    private static void tirarRuleta(Jugador jugador, Scanner scanner) {
        /**
         * Probabilidades de la ruleta. 0 - 7.5 --> Quiebra 7.6 - 15 --> Perder
         * el turno 15.1 - 25 --> 0€ 25.1 - 50 --> 25€ 50.1 - 65 --> 50€ 65.1 -
         * 75 --> 75€ 75.1 - 80 --> 100€ 80.1 - 83.75 --> 150€ 83.76 - 87.5 -->
         * 200 87.6 - 95 --> Vuelve a tirar 95.1 - 100 --> Vocal gratis, vuelve
         * a tirar.
         */
        double opcionRuleta = Math.random() * 100;

        if (opcionRuleta >= 0 && opcionRuleta <= 7.5) {
            System.out.println("Has caido en quiebra");
            jugador.setBote(0);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 7.6 && opcionRuleta <= 15) {
            System.out.println("Pierdes el turno");
            turnoJugadorVivo = false;
        } else if (opcionRuleta >= 15.1 && opcionRuleta <= 25) {
            System.out.println("0€");
        } else if (opcionRuleta >= 25.1 && opcionRuleta <= 50) {
            System.out.println("25€");
            jugador.setBote(jugador.getBote() + 25);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 50.1 && opcionRuleta <= 65) {
            System.out.println("50€");
            jugador.setBote(jugador.getBote() + 50);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 65.1 && opcionRuleta <= 75) {
            System.out.println("75€");
            jugador.setBote(jugador.getBote() + 75);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 75.1 && opcionRuleta <= 80) {
            System.out.println("100€");
            jugador.setBote(jugador.getBote() + 100);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 80.1 && opcionRuleta <= 83.75) {
            System.out.println("150€");
            jugador.setBote(jugador.getBote() + 150);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 83.76 && opcionRuleta <= 87.5) {
            System.out.println("200€");
            jugador.setBote(jugador.getBote() + 200);
            System.out.println("Actualmente el bote es de: " + jugador.getBote());
        } else if (opcionRuleta >= 87.6 && opcionRuleta <= 95) {
            System.out.println("Vuelves a tirar");
        } else if (opcionRuleta >= 95.1 && opcionRuleta <= 100) {
            System.out.println("Vocal Gratis");
            // Le doy 50€ para simular que es "gratis".
            jugador.setBote(jugador.getBote() + 50);
            comprarVocal(jugador, scanner);
        }
    }

    /**
     * Intenta resolver el panel escribiéndolo por teclado.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner para pulsar "enter" y tirar.
     */
    private static void resolverPanel(Jugador jugador, Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Compra una vocal verificando si tiene dinero para ello. También comprueba
     * si quedan vocales por comprar o no.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner para pulsar "enter" y tirar.
     */
    private static void comprarVocal(Jugador jugador, Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void buildPaneles(String[] paneles) {
        paneles[0] = "Yo solo se que no se nada";
        paneles[1] = "Cristiano Ronaldo";
        paneles[2] = "La generacion del veintisiete";
        paneles[3] = "Tortilla de patatas";
        paneles[4] = "La teoria del todo";
        paneles[5] = "Un ciudadano ejemplar";
    }

    private static void buildPustas(String[] pistas) {
        pistas[0] = "Socrates";
        pistas[1] = "Ex-Jugador del Real Madrid";
        pistas[2] = "Conjunto de escritores";
        pistas[3] = "Comida tipica";
        pistas[4] = "Stephen Hawking";
        pistas[5] = "Pelicula";
    }

}
