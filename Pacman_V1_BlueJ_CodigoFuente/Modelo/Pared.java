/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * Esta clase se encarga de crear una pared del laberinto y establecer su posicion en el mismo
 * asi como el tipo de pared que es (esquina inferior , superior, etc.).Hereda de la clase Objetos
 * @author Juan Paulo
 */
public class Pared extends Objetos {
    
 
    private int tipo; // tipo de pared
    /**
     * Constructor
     * @param pos posicion en el mapa
     */
    public Pared(Posicion pos) {
        super(pos);
    }
    /**
     * 
     * @param tipo tipo de pared
     * @param pos posicion del objeto en el mapa
     * @param ancho ancho del objeto
     * @param alto alto del objeto
     */
    public Pared(int tipo, Posicion pos, int ancho, int alto) {
        super(ancho,alto,pos);
        this.tipo = tipo;
    }
    

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }


    
}
