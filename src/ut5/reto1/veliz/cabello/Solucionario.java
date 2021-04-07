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
public class Solucionario {
    
    /**
     * Defino la variable como estática, 
     * para que sea el mismo número aleatorio 
     * cada vez que lo pida en un mismo juego.
     */
    static int n = (int)(Math.floor(Math.random() * 4) + 1); //Aleatorio del 1 al 3
    
    /**
     * Comprobar de que la solución va acorde a la pista.
     * @param args 
     * Solo he escrito 4 soluciones, pero se podría expandir hasta las deseadas.
     * Para ello, solo añadimos más elementos y aumentamos el rango
     * del número aleatorio generado en "n".
     */
    public static void main(String[] args) {
        System.out.println(solucion());
        System.out.println(pista());
    }
    
    /**
     * Crea una solución aleatoria
     * @return la solución
     */
    public static String solucion(){
        String solucion;
        switch(n){
            case 1:
                solucion="mario";
                break;
            case 2:
                solucion="agustin";
                break;
            case 3:
                solucion="maestre";
                break;
            case 4:
                solucion="veliz";
                break;
            default:
                solucion="veliz";
        }
        return solucion;
    }
    
    /**
     * Crea una pista aleatoria
     * @return la pista
     */
    public static String pista(){
        String pista;
        switch(n){
            case 1:
                pista="El fontanero más famoso.";
                break;
            case 2:
                pista="Profesor de ENDES y Programación.";
                break;
            case 3:
                pista="Instituto en el que estudias.";
                break;
            case 4:
                pista="Autor del trabajo.";
                break;
            default:
                pista="Autor del trabajo.";
        }
        return pista;
    }
}
