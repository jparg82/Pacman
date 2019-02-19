/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * punto grande del laberinto .Hereda de la clase Objetos
 * @author Juan Paulo
 */
public class puntoGrande extends Objetos{
    
    private int tiempo; // tiempo que dura la habilidad del punto grande
    
    /**
     * Constructor de la clase puntoGrande .Hereda de la clase Objetos
     * @param pos posicion del objeto en el mapa
     * @param ancho ancho del objeto
     * @param alto alto del objeto
     */
    public puntoGrande(Posicion pos, int ancho, int alto) {
        super(ancho,alto,pos);
        tiempo=5000;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

}
