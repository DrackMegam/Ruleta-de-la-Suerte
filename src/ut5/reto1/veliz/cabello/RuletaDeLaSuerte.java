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
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author DrackMegam
 * @author Javicct1
 */
public class RuletaDeLaSuerte {

    // Variables para controlar el curso del juego.
    static boolean turnoJugadorVivo = true;

    public static void main(String[] args) {
        Scanner usu = new Scanner(System.in);
        String respuestaUsuario;
        int numeroJugadores = 0;
        int numeroPaneles = 0;
        // Para verificar el nº de jugadores y la acción del turno de estos.
        boolean verificarAccion;
        // Paneles
        String[] paneles = new String[6];
        String[] pistas = new String[6];
        buildPaneles(paneles);
        buildPustas(pistas);
        // Para que empiece a coger por X panel/pista.
        int panelAleatorio = (int) (Math.random() * 6);
        panelAleatorio++; // Evita repetir paneles.
        Set<Character> vocalRepetida = new TreeSet<>();
        Set<Character> consonanteRepetida = new TreeSet<>();

        // Inicio del juego.
        System.out.println("BIENVENIDA");

        // Elegir jugadores.
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

        // Elegir paneles.
        do {
            verificarAccion = false;
            System.out.printf("Nº paneles: ");
            respuestaUsuario = usu.nextLine();

            // Verifico la respuesta del usuario.
            if (respuestaUsuario.equals("1")
                    || respuestaUsuario.equals("2")
                    || respuestaUsuario.equals("3")
                    || respuestaUsuario.equals("4")
                    || respuestaUsuario.equals("5")) {
                verificarAccion = true;
            }
        } while (!verificarAccion);
        numeroPaneles = Integer.parseInt(respuestaUsuario);

        // Ahora selecciono los paneles y los guardo en un stack.
        Stack<String> panelesElegidos = new Stack<>();
        Stack<String> pistasElegidos = new Stack<>();
        for (int i = 0; i < numeroPaneles; i++) {
            // Para evitar salirnos del array al sumar "i".
            if ((panelAleatorio + i) == 6) {
                panelAleatorio = 0;
            }
            panelesElegidos.add(paneles[panelAleatorio + i]);
            pistasElegidos.add(pistas[panelAleatorio + i]);
        }

        // COMPROBAR QUE LOS PANELES SE GENEREN BIEN
//        System.out.println("[TRAZA]");
//        while(!panelesElegidos.empty()){
//            System.out.printf("PANEL: ");
//            System.out.printf(panelesElegidos.pop());
//            System.out.println();
//        }
//        while(!pistasElegidos.empty()){
//            System.out.printf("PISTA: ");
//            System.out.printf(pistasElegidos.pop());
//            System.out.println();
//        }
//        System.out.println("[TRAZA]");
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
            Jugador jugador = new Jugador(nombresJugadores.poll(), 0, 0);
            jugadores.add(jugador);
        }

        // Aquí comienza el juego.
        // Turno del jugador que empieza, y para ver como avanzan los turnos..
        int turnoJugador = (int) (Math.random() * numeroJugadores);

        System.out.println("Le toca al jugador...");
        System.out.println("¡" + jugadores.get(turnoJugador).getNombreJugador() + "!");

        // Para guardar el panel que se juega actualmente.
        ArrayList<String> panelSolucion = new ArrayList<>();

        respuestaUsuario = null;
        int accionJugador;
        boolean primeraAccion = true;
        do { // Bucle de Juego
            primeraAccion = true;
            // Añado el panel que se va a jugar ahora.
            for (int i = 0; i < panelesElegidos.peek().length(); i++) {
                panelSolucion.add("_");
            }

            do { // Bucle de Turno
                turnoJugadorVivo = true;
                verificarAccion = false;
                // Invoco el texto personalizado.
                Texto.comenzarTurno(jugadores.get(turnoJugador));

                // Le obligo a tirar de la ruleta.
                // Pero solo si es la 1º acción de su turno.
                if (primeraAccion) {
                    Texto.tirarRuleta(jugadores.get(turnoJugador));
                    tirarRuleta(jugadores.get(turnoJugador), usu, vocalRepetida, consonanteRepetida);
                }
                primeraAccion = false;

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
                            tirarRuleta(jugadores.get(turnoJugador), usu, vocalRepetida, consonanteRepetida);
                            break;
                        case 2:
                            Texto.resolverPanel(jugadores.get(turnoJugador));
                            // TODO Resolver el panel y cagar los demáses boteses.
                            // Aquí hay que meter partes del ahorcado.
                            resolverPanel(jugadores.get(turnoJugador), usu,
                                    panelesElegidos.peek(), pistasElegidos.peek());
                            // Si está vivo, ha resuelto el panel.
                            if (turnoJugadorVivo) {
                                panelesElegidos.pop();
                                pistasElegidos.pop();
                                if (panelesElegidos.empty()) {
                                    turnoJugadorVivo = false;
                                }
                            }
                            break;
                        case 3:
                            // TODO Antes de comprar una vocal, hay que ver si
                            // aún quedan vocales o si tiene más de 50 pavos.
                            // en un IF, claro está
                            Texto.comenzarTurno(jugadores.get(turnoJugador));
                            // TODO Comprar una vocal, comprobando bote y demáses.
                            comprarVocal(jugadores.get(turnoJugador), usu, vocalRepetida);
                            break;
                    }
                }

            } while (turnoJugadorVivo);
            turnoJugador = avanzarTurno(turnoJugador, numeroJugadores - 1);
        } while (!panelesElegidos.empty());

        System.out.println();
        estadoPartida(jugadores, numeroJugadores);
        System.out.println("FIN DE LA PUTA VIDA");
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
     * @param consonanteRepetida Consonantes dichas con anterioridad.
     * @param vocalRepetida Vocales dichas con anterioridad.
     */
    private static void tirarRuleta(Jugador jugador, Scanner scanner, Set<Character> vocalRepetida, Set<Character> consonanteRepetida) {
        /**
         * Probabilidades de la ruleta. 0 - 7.5 --> Quiebra 7.6 - 15 --> Perder
         * el turno 15.1 - 25 --> 0€ 25.1 - 50 --> 25€ 50.1 - 65 --> 50€ 65.1 -
         * 75 --> 75€ 75.1 - 80 --> 100€ 80.1 - 83.75 --> 150€ 83.76 - 87.5 -->
         * 200 87.6 - 95 --> Vuelve a tirar 95.1 - 100 --> Vocal gratis, vuelve
         * a tirar.
         */
        double opcionRuleta = Math.random() * 100;
        // Para tirar de la ruleta.
        String tirarRuletita = scanner.nextLine();

        if (opcionRuleta >= 0 && opcionRuleta <= 7.5) {
            System.out.println("Has caido en quiebra");
            jugador.setBote(0);
            turnoJugadorVivo = false;
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 7.6 && opcionRuleta <= 15) {
            System.out.println("Pierdes el turno");
            turnoJugadorVivo = false;
        } else if (opcionRuleta >= 15.1 && opcionRuleta <= 25) {
            System.out.println("+0€");
            decirConsonante(jugador, scanner, consonanteRepetida);
        } else if (opcionRuleta >= 25.1 && opcionRuleta <= 50) {
            System.out.println("+25€");

            decirConsonante(jugador, scanner, consonanteRepetida);
            //Si el turno no esta vivo es porque ha repetido consonante/quiebra/pierde el turno
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 25);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 50.1 && opcionRuleta <= 65) {
            System.out.println("+50€");
            decirConsonante(jugador, scanner, consonanteRepetida);
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 50);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 65.1 && opcionRuleta <= 75) {
            System.out.println("+75€");
            decirConsonante(jugador, scanner, consonanteRepetida);
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 75);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 75.1 && opcionRuleta <= 80) {
            System.out.println("+100€");
            decirConsonante(jugador, scanner, consonanteRepetida);
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 100);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 80.1 && opcionRuleta <= 83.75) {
            System.out.println("+150€");
            decirConsonante(jugador, scanner, consonanteRepetida);
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 150);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 83.76 && opcionRuleta <= 87.5) {
            System.out.println("+200€");
            decirConsonante(jugador, scanner, consonanteRepetida);
            if (turnoJugadorVivo) {
                jugador.setBote(jugador.getBote() + 200);
            }
            System.out.println("Bote: " + jugador.getBote());
        } else if (opcionRuleta >= 87.6 && opcionRuleta <= 95) {
            System.out.println("Vuelves a tirar");
        } else if (opcionRuleta >= 95.1 && opcionRuleta <= 100) {
            System.out.println("Vocal Gratis");
            // Le doy 50€ para simular que es "gratis".
            jugador.setBote(jugador.getBote() + 50);
            comprarVocal(jugador, scanner, vocalRepetida);
        }
    }

    /**
     * Intenta resolver el panel escribiéndolo por teclado.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner para pulsar "enter" y tirar.
     * @param panel Panel actual en juego.
     * @param pista Pista del panel relaccionado.
     */
    private static void resolverPanel(Jugador jugador, Scanner scanner, String panel, String pista) {
        Texto.mostrarPista(pista);
        String respuestaFinal = scanner.nextLine();

        if (respuestaFinal.equalsIgnoreCase(panel)) {
            System.out.println("PANEL RESUELTO");
            jugador.setDinero(jugador.getDinero() + jugador.getBote());
            jugador.setBote(0);
        } else {
            System.out.println("HAS FALLADO");
            turnoJugadorVivo = false;
        }

    }

    /**
     * Compra una vocal verificando si tiene dinero para ello. También comprueba
     * si quedan vocales por comprar o no.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner para pulsar "enter" y tirar.
     */
    private static void comprarVocal(Jugador jugador, Scanner scanner, Set<Character> vocalRepetida) {
        if (jugador.getBote() >= 50) {
            System.out.println("Dime la vocal que quieres comprar");
            Character vocal = scanner.next().charAt(0);
            if (vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u'
                    || vocal == 'A' || vocal == 'E'
                    || vocal == 'I' || vocal == 'O'
                    || vocal == 'U') {
                if (vocalRepetida.contains(vocal)) {
                    System.out.println("Esta repetida");
                } else {
                    vocalRepetida.add(vocal);
                    jugador.setBote(jugador.getBote() - 50);
                }

            } else {
                System.out.println("No has dicho una vocal");
            }
        } else {
            System.out.println("No tienes dinero");
        }
    }

    /**
     * Construye el array de paneles.
     *
     * @param paneles Panel a rellenar.
     */
    private static void buildPaneles(String[] paneles) {
        paneles[0] = "Yo solo se que no se nada";
        paneles[1] = "Cristiano Ronaldo";
        paneles[2] = "La generacion del veintisiete";
        paneles[3] = "Tortilla de patatas";
        paneles[4] = "La teoria del todo";
        paneles[5] = "Un ciudadano ejemplar";
    }

    /**
     * Construye el array de pistas.
     *
     * @param pistas Pistas a rellenar.
     */
    private static void buildPustas(String[] pistas) {
        pistas[0] = "Socrates";
        pistas[1] = "Ex-Jugador del Real Madrid";
        pistas[2] = "Conjunto de escritores";
        pistas[3] = "Comida tipica";
        pistas[4] = "Stephen Hawking";
        pistas[5] = "Pelicula";
    }

    /**
     * Pide al usuario una consonante.
     *
     * @param jugador Jugador del turno actual.
     * @param scanner Scanner normal.
     * @param consonanteRepetida Para comprobar que la consonante no sea
     * repetida.
     */
    public static void decirConsonante(Jugador jugador, Scanner scanner, Set<Character> consonanteRepetida) {
        System.out.println("Dime la consonante");
        Character consonante = scanner.nextLine().charAt(0);
        if (consonante != 'a' || consonante != 'e' || consonante != 'i' || consonante != 'o' || consonante != 'u'
                || consonante != 'A' || consonante != 'E'
                || consonante != 'I' || consonante != 'O'
                || consonante != 'U') {
            if (consonanteRepetida.contains(consonante)) {
                System.out.println("Esta repetida");
                turnoJugadorVivo = false;
            } else {
                consonanteRepetida.add(consonante);

            }
        } else {
            System.out.println("No has dicho una consonante");
        }
    }

}

