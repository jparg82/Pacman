/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * Clase posicion
 * Estas clase se guarda la posicion haciendo referencia a las cooordenadas x e y
 * @author Juan Paulo
 */
public class Posicion {
    
    private int x; // coordenada x
    private int y; // coordenada y
    /**
     * Constructor 
     */
    public Posicion() {
    }
    /**
     * Constructor
     * @param x coordenada x
     * @param y coordenada y
     */
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
