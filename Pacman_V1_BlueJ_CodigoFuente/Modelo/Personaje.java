/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * Clase de contener las caracteristicas y funcionalidades basicas de un personaje arcade
 * @author Juan Paulo
 */
public abstract class Personaje extends Objetos {
    
    protected int direccion;  // direccion en la que mueve un personaje, 1=izquierda, 2=dcerecha ,3=arriba,4=abajo 
    protected int velocidad; // velocidad a la que se mueve un personaje
    protected Posicion posicionInicial;// posicion inicial del personaje
    protected Mapa mapa;// mapa por el que se mueve un personaje
    /**
     * Constructor
     * @param pos posicion del objeto en el mapa
     */
    public Personaje(Posicion pos) {
        super(pos);
        posicionInicial = new Posicion(pos.getX(),pos.getY());
    }
    /**
     * 
     * @param pos posicion del objeto en el mapa
     * @param ancho ancho del objeto
     * @param alto alto del objeto
     */
    public Personaje(Posicion pos, int ancho, int alto) {
        super(ancho,alto,pos);
        posicionInicial = new Posicion(pos.getX(),pos.getY());
    }
    /**
     * Constructor
     * @param pos posicion del objeto en el mapa
     * @param ancho ancho del objeto
     * @param alto alto del objeto
     * @param velocidad velocidad a la que se mueve el objeto
     * @param mapa mapa por el que se mueve el objeto
     */
    public Personaje(Posicion pos, int ancho, int alto,int velocidad, Mapa mapa) {
        super(ancho,alto,pos);
        this.mapa=mapa;
        this.velocidad=velocidad;
        posicionInicial = new Posicion(pos.getX(),pos.getY());
    }
    
    /**
     * Establece la posicion inicial del jugador
     */
    public void posicionInicial(){
        //pos = new Posicion (30,30);
        setPos(new Posicion (getPosicionInicial().getX(),getPosicionInicial().getY()));
    }
    /**
     * Metodo que comprueba que el personaje no salga de los limites del mapa
     * Se soobrescribe en la clase fantasma
     */
    protected void comprobarLimites(){
        // si el personaje supera los limites del mapa cambia la posiscion del personaje dependiendo
        // donde supera el limite
        if(this.getPos().getX()<0){
            this.getPos().setX(this.getMapa().getAncho());
        }
        if(this.getPos().getX()>this.getMapa().getAncho()){
              this.getPos().setX(0);
        }  
    }
    /**
     * Metodo para mover a un personaje por el mapa
     * hay que redefinirlo en las subclases que hereden de esta clase
     */
    public void mover(){};

    /**
     * @return the direccion
     */
    public int getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the posicionInicial
     */
    public Posicion getPosicionInicial() {
        return posicionInicial;
    }

    /**
     * @param posicionInicial the posicionInicial to set
     */
    public void setPosicionInicial(Posicion posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @param mapa the mapa to set
     */
    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}
