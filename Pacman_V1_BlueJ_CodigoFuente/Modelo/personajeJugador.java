/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * Calse que se encarga de crear los campos y metodos basicos para cualquier personaje principal 
 * de un juego tipo arcade. La clase Heroe hereda de de esta clase. Esta clase hereda de la clase Personaje
 * @author Juan Paulo
 */
public abstract class personajeJugador extends Personaje{

    protected int puntuacion; // puntuacion del personaje
   
    private int vidas; // vidas del personaje
    
    private Teclado teclado; // controlador de teclado 
   
    /**
     * Constructor
     * @param pos posicion del objeto en el mapa
     * @param ancho ancho del objeto
     * @param alto alto del objeto
     * @param velocidad velocidad a la que se mueve el objeto
     * @param mapa mapa por el que se mueve el objeto
     * @param vidas vidas del jugador
     * @param teclado teclado que utiliza el juegador para mover el objeto
     */
    public personajeJugador(Posicion pos, int ancho, int alto, int velocidad, Mapa mapa, int vidas,Teclado teclado) {
        super(pos, ancho, alto, velocidad, mapa);
        this.teclado=teclado;
        this.vidas = vidas;
    }
    
    /**
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    

    /**
     * @return the teclado
     */
    public Teclado getTeclado() {
        return teclado;
    }
    
    /**
     * @return the vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }


}
