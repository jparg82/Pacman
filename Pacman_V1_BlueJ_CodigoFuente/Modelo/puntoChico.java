/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * Punto chico del laberinto.Hereda de la clase Objetos
 * @author Juan Paulo
 */
public class puntoChico extends Objetos{
    
    private int valor; // valor de un punto
    /**
     * Constructor de la clase puntoChico
     * @param pos posicion del objeto en el mapa
     * @param ancho acncho del objeto
     * @param alto alto del objeto
     */
    public puntoChico( Posicion pos, int ancho, int alto) {
        super(pos);
        valor=10;
        this.alto=alto;
        this.ancho=ancho;
        
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }
  
}
