package ut5.reto1.veliz.cabello;

import java.text.Normalizer; //Para quitar tildes
import java.util.Arrays;
// https://stackoverflow.com/questions/3322152/is-there-a-way-to-get-rid-of-accents-and-convert-a-whole-string-to-regular-lette/15191508#:~:text=Use%20java.&text=string%20%3D%20string.,string%20%3D%20string.
import java.util.Scanner;
import java.util.TreeSet;
import static ut5.reto1.veliz.cabello.Solucionario.pista;
import static ut5.reto1.veliz.cabello.Solucionario.solucion;

/**
 * Reto 3 - Ahorcado. En este ahorcado, pese tener el código base del reto
 * anterior, he modificado el como se estructura el código. Su funcionamiento es
 * el mismo, pero añadiendo las mejoras del reto 3.
 *
 * @author DrackMegam
 */
public class Ahorcado {

    // Definición de variables globales, por no andar metiendolas en por
    // parámetro y por pereza mia.
    private static String solucion = solucion();
    private static String pista = pista();
    private static char solucionArray[] = new char[solucion.length()];
    // Esta variable funciona de tal manera que:
    // --> Si es par, es un fallo completo.
    // --> Si es impar, se asume que solo tiene "medio fallo".
    // Un fallo parcial es, o repetir una letra, o decir una vocal.
    private static int fallosParciales = 0;

    public static void main(String[] args) {
        System.out.println("Vamos a jugar al ahorcado.");
        comenzarAhorcado();
    }

    /**
     * Inicia un nuevo juego y pide respuestas al usuario hasta finalizar. El
     * juego puede acabar de dos formas: o bien el usuario escribe la respuesta
     * completa, o bien falla X veces hasta perder el juego. En ambos casos, el
     * juego finaliza y se cierra.
     *
     *
     * Existe un bug por el cual las tildes no se eliminan en NetBeans. Sin
     * embargo, al ejecutar el ".jar" en consola, si funciona correctamente.
     *
     */
    public static void comenzarAhorcado() {
        // Comienzo el patíbulo y declaro variables.
        Patibulo juego = new Patibulo();
        Scanner usu = new Scanner(System.in);
        String respuesta;
        int opcionUsuario;
        char letraRespuesta;

        // Relleno el array con "_" para indicar letras desconocidas.
        for (int i = 0; i < solucionArray.length; i++) {
            solucionArray[i] = '_';
        }

        // Para acumular las letras que ha dicho el usuario.
        TreeSet<Character> letrasDichas = new TreeSet<>();

        System.out.println("Dime una consonante, o la solución.");
        System.out.println("NOTA: no vale escribir vocales.");
        System.out.println();
        OUTER:
        do {
            imprimirMenu();
            opcionUsuario = usu.nextInt();
            switch (opcionUsuario) {
                case 1:
                    // Resolver el ahorcado de tirón.
                    System.out.printf("Solución |> ");
                    respuesta = usu.next();
                    if (respuesta.equalsIgnoreCase(solucion)) {
                        System.out.println("¡HAS GANADO!");
                        break OUTER;
                    } else {
                        apuntarFallo(juego);
                    }
                    break;
                case 2:
                    // Escribir una única letra.
                    do {
                        System.out.printf("Letra |> ");
                        respuesta = usu.next();
                        respuesta = normalizarString(respuesta);
                        // Obtengo el caracter dicho.
                        letraRespuesta = respuesta.charAt(0);

                        // Compruebo que no esté en el set.
                        if (letrasDichas.contains(letraRespuesta)) {
                            System.out.println("¡Ya has dicho esa letra!");
                            apuntarFalloParcial(juego);
                            // "Nulifico" la respuesta para salir del bucle.
                            respuesta = "";
                        } else {
                            // Si no está, procedo, añadiendo la letra al set.
                            letrasDichas.add(letraRespuesta);
                            if (comprobarVocal(letraRespuesta)) {
                                System.err.println("Vocal escrita.");
                                apuntarFalloParcial(juego);
                            }
                            respuesta = "";
                            // Ahora, miro si dicha letra coincide.
                            if (!coincidencia(letraRespuesta, solucion)) {
                                apuntarFallo(juego);
                            }
                        }
                    } while (respuesta.length() != 0);
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        } while (juego.isAlive());

        // Al llegar aquí, el juego ha debido de acabar.
        System.out.println();
        System.out.println("FIN DEL JUEGO");
    }

    /**
     * Busca coincidencia en la respuesta del usuario. El IF lo uso debido a
     * que, al escribir la solución correcta, no se muestre si el primer
     * caracter de dicha solución "está o no en la solución".
     *
     * @param letra Primer carácter del input del usuario.
     * @param solucion Solución del problema del ahorcado
     * @return TRUE si existe coincidencia, FALSE si no.
     */
    public static boolean coincidencia(char letra, String solucion) {
        // -1 para evitar salirme del String y provocar fallos.
        for (int i = solucion.length() - 1; i >= 0; i--) {
            if (letra == (solucion.charAt(i))) {
                // +1 para decir la posición real.
                System.out.println();
                System.out.println("Existe una <" + letra + "> en posición: " + (i + 1));
                // Actualizo la solución y la muestro.
                solucionArray[i] = letra;
                System.out.println(Arrays.toString(solucionArray));
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si una letra es una vocal o no.
     *
     * @param letraRespuesta Letra a constrastar
     * @return TRUE si es vocal, FALSE si no lo es.
     */
    private static boolean comprobarVocal(char letraRespuesta) {
        if (letraRespuesta == 'a' || letraRespuesta == 'e'
                || letraRespuesta == 'i' || letraRespuesta == 'o'
                || letraRespuesta == 'u'
                || letraRespuesta == 'A' || letraRespuesta == 'E'
                || letraRespuesta == 'I' || letraRespuesta == 'O'
                || letraRespuesta == 'U') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Apunta un fallo en el patíbulo y comprueba si quedan "vidas"
     *
     * @param juego Patíbulo a comprobar.
     */
    private static void apuntarFallo(Patibulo juego) {
        System.out.println(juego.fails());
        if (juego.isDead()) {
            System.out.println("Has perdido... F");
        }
    }

    /**
     * Apunta un fallo parcial. También comprueba si hay dos fallos parciales,
     * formando así un fallo entero y penalizando al jugador de forma
     * correspondiente.
     *
     * Existe un bug en el que AVECES el "Penalizando..." corta visualmente
     * el patíbulo, pero al ejecutar el ".jar" en la consola, no ocurre.
     * 
     * @param juego Patíbulo en el que se está jugando actualmente.
     */
    private static void apuntarFalloParcial(Patibulo juego) {
        fallosParciales++;
        if (fallosParciales % 2 == 0) {
            System.err.println("Penalizando...");
            apuntarFallo(juego);
        }
    }

    /**
     * Normaliza un String. Elimina tildes, espacios y mayúsculas del String.
     *
     * @param stringRaro String a normalizar
     * @return String normalizado.
     */
    private static String normalizarString(String stringRaro) {
        stringRaro = stringRaro.trim().toLowerCase();
        stringRaro = Normalizer.normalize(stringRaro, Normalizer.Form.NFD);
        stringRaro = stringRaro.replaceAll("´", "");
        return stringRaro;
    }

    /**
     * Imprime el menú a cada paso del jugador.
     */
    private static void imprimirMenu() {
        System.out.println("PISTA: " + pista);
        System.out.println();
        System.out.println("1. Resolver el ahorcado");
        System.out.println("2. Escribir letra");
        System.out.printf("||> ");
    }

}
