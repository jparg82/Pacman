/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Vista.Pantalla;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Esta clase es la clase padre de las que heredaran los distintos tipos de objetos del juego ,
 * contiene las características básicas de un objeto dibujable en el tablero
 * @author Juan Paulo
 */
public abstract class Objetos {
    
    protected  int ancho; // ancho del objeto
    protected  int alto; // alto del objeto
    protected Posicion pos; // posicion del objeto
    protected Image imagen; // imagen del objeto
    
    /**
     * Constructor de la clase objetos
     * @param pos posicion en el tablero de juego
     */
    public Objetos(Posicion pos) {
        this.pos = pos;
    }
    /**
     * Contructor 
     * @param ancho ancho del objeto e
     * @param alto alto del objeto
     * @param pos posicion en el tablero de juego
     */
    public Objetos(int ancho, int alto, Posicion pos) {
        this.ancho = ancho;
        this.alto = alto;
        this.pos = pos;
    }
    /**
     * 
     * @param ancho ancho del objeto e
     * @param alto alto del objeto
     * @param pos posicion en el tablero de juego
     * @param imagen imagen del objeto
     */
    public Objetos(int ancho, int alto, Posicion pos, Image imagen) {
        this.ancho = ancho;
        this.alto = alto;
        this.pos = pos;
        this.imagen = imagen;
    }

    /**
     * Devuelve al objeto como un rectangulo
     * @param x coordenada x
     * @param y coordenada y
     * @param ancho ancho 
     * @param alto alto
     * @return rectangulo
     */
    public Rectangle getBounds(int x, int y, int ancho, int alto){
        
        return new Rectangle(x,y,ancho,alto);
    }
    /**
     * Dibuja el objeto
     * @param g objeto Graphics
     * @param p pantalla donde se dibuja
     */
    public void Dibujar(Graphics g, Pantalla p) {
        g.drawImage(getImagen(), getPos().getX(), getPos().getY(), p);
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }
    
     /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return the pos
     */
    public Posicion getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Posicion pos) {
        this.pos = pos;
    }
    
     /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
}
