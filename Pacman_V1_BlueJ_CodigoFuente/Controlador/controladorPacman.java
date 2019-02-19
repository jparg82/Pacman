/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Fantasma;

import Modelo.Mapa;
import Modelo.Heroe;
import Modelo.Objetos;
import Modelo.Posicion;
import Modelo.Teclado;
import Modelo.puntoChico;
import Modelo.puntoGrande;
import Vista.Pantalla;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

/**
 * Clase que controla las acciones y acutaliza el estado de pacman(modelo) durante el juego 
 * @author Juan Paulo
 */
public class controladorPacman implements ActionListener{
    
    private Heroe pacman;// pacma que controla el jugador
    private ArrayList<Fantasma> fantasmas; // lista de fantasmas que mueve el ordenador
    private Timer timerAzul;// timer con el tiempo que duran los fantasmas en modo azul
    private boolean gana;// indica si el jugador gana o no la partida
    private boolean fin; // indica si es el fin de la partida
    
    /**
     * Constructor
     * @param laberinto mapa del juego
     * @param fantasmas lista de fantasmas
     */
    public controladorPacman(Mapa laberinto, ArrayList<Fantasma> fantasmas) {
        this.fantasmas = fantasmas;
        gana=false;
        fin=true;
        pacman = new Heroe( new Posicion(300,480), 30,30,1,laberinto,1,new Teclado()); 
    }

     /**
     * Metodo que comprueba si pacman come puntos.
     * realiza la función de pacman de comer puntos según si este colisiona con algún punto
     */
    private void comer(){
        
        Iterator puntos = pacman.getMapa().getPuntos().iterator();
        boolean come =false;
        // se recorre la lista de puntos mientras pacman no haya comido algun punto o se llegue al final de la listta
        while (puntos.hasNext() && !come){
            Objetos p = (Objetos) puntos.next();
            Rectangle pr = p.getBounds(p.getPos().getX(), p.getPos().getY(), p.getAncho(), p.getAlto());
            Rectangle pacr = getPacman().getBounds(getPacman().getPos().getX(), getPacman().getPos().getY(), getPacman().getAncho(), getPacman().getAlto());
            // si pacman colisiona con un punto se comprueba si el punto comido es chico o grande
            if(pacr.contains(pr.getCenterX(),pr.getCenterY())  ){        
                if(p instanceof puntoGrande){
                    come=true;
                        // si el punto comido es grande se modifica la velocidad de pacman
                        if(pacman.getPos().getX()%30==0 && pacman.getPos().getY()%30==0){
                            puntoGrande pg = (puntoGrande) p;
                            getPacman().setVelocidad(2);
                            // se cambian los fantasmas al modo azul
                            establecerFantasmasAzules(pg);
                            puntos.remove(); 
                        }

                }else{
                    // si el punto comido es chico se añade a la lista de puntos comidos de pacman
                    if(p instanceof puntoChico){
                            puntoChico pc = (puntoChico) p;
                            getPacman().addPuntos(pc);
                            come=true;
                            puntos.remove();
                    }
                    
                }   
            }
   
        }
                
    }
    /**
     * Metodo que pone a los fantasmas en el modo azul
     * @param pg punto grande que establece la duracion del tiempo que los fantasmas son azules
     */
    private void establecerFantasmasAzules(puntoGrande pg) {
        Iterator fan = fantasmas.iterator();
        // se cambian los fantasmas al modo azul                  
        while(fan.hasNext()){
            Fantasma f= (Fantasma) fan.next();
            f.setAzul(true);
            
        }
         // y se inicia el timer con el tiempo de duracion del modo azul de los fantasmas
        timerAzul = new Timer(pg.getTiempo(),this);
        timerAzul.setRepeats(false);
        timerAzul.start();
    }
    /**
     * Metodo que establece si pacman gana o no la partida
     */
    public void pacmanGana(){
        // si pacman comio todos los puntos pacman gana y es el fin del juego
        if(pacman.getMapa().getPuntos().isEmpty()){
            this.setFin(true);
            this.setGana(true);
        }else{
            //en caso contrario pacman no gana y se comprueba si colisiona con algun fantasma modificando
            // la variable fin 
            this.setGana(false);
            this.setFin(colisionFantasmasPacman());
        }
    }
    /**
     *  Metodo que comprueba si pacman colisiono con algun fantasma
     * @return true si colisiona con un fantasma
     */
    private boolean colisionFantasmasPacman(){
        boolean colision = false; // indica si pacman colisiono con algun fantasma
        // se recorre la lista de fantasmas
        for(Fantasma f:fantasmas){
            Rectangle pacr = getPacman().getBounds(getPacman().getPos().getX(), getPacman().getPos().getY(), getPacman().getAncho(), getPacman().getAlto());
            Rectangle fr = f.getBounds(f.getPos().getX(), f.getPos().getY(), f.getAncho(), f.getAlto());
            // si el fantasma con el que colisiona pacman es azul se añade a la lista de fantasmas
            // comidos y vuelven a la posicion inicial(casa)
            if(f.isAzul()){
                 if(pacr.intersects(fr)){
                    f.posicionInicial();
                    f.setAzul(false);  
                    this.pacman.addFantasmasComidos(f);
                 }
            }else{
                // en caso contrario si el el fantasma no es azul colision se establece true
                if(pacr.intersects(fr.getMinX(),fr.getMinY(),fr.getWidth(),fr.getHeight())){
                    // se resta una vida al jugador
                    int vidas = getPacman().getVidas();
                    vidas--;
                    getPacman().setVidas(vidas);
                    colision=true;
                }    
            }
            
        }
        return colision;
    }
   
    /**
     * Asigna la direccion de moviento segun la tecla pulsada por el jugador
     */
    private void direccionMovimiento(){
        // se obtiene el moviemiento que ha pulsado el jugador para que se mueva pacman
        int dir = pacman.getTeclado().getMove();
        
        Mapa mapa= pacman.getMapa();
        // se modifica la direccion de movimiento en funcion de la tecla pulsada siempte que en esa direccion no choque con una pared
        switch (dir){
            case 1:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX()-pacman.getVelocidad(),pacman. getPos().getY()) ) {
                    pacman.setDireccion(dir);
                    
                }
                break;
            case 2:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX()+pacman.getVelocidad(),pacman. getPos().getY()) ) {
                    pacman.setDireccion(dir);
                   
                }
                break;
            case 3:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX(),pacman. getPos().getY()-pacman.getVelocidad()) ) {
                    pacman.setDireccion(dir);
               
                }
                break;
            case 4:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX(),pacman. getPos().getY()+pacman.getVelocidad()) ) {
                    pacman.setDireccion(dir);
                    
                }
                break;
        }
    }
    /**
     * Actualiza la posicion de pacman
     */
    private void movimiento(){
        Mapa mapa= pacman.getMapa();
        direccionMovimiento();
        switch (pacman.getDireccion()){
            // se actualiza el estado de la posicion de pacman en funcion del codigo de direccion
            case 1:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX()-pacman.getVelocidad(),pacman. getPos().getY()) ) {
                    getPacman().mover();  
                    
                }
                break;
            case 2:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX()+pacman.getVelocidad(),pacman. getPos().getY()) ) {
                    getPacman().mover();  
                   
                }
                break;
            case 3:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX(),pacman. getPos().getY()-pacman.getVelocidad()) ) {
                    getPacman().mover();  
               
                }
                break;
            case 4:
                if(!mapa.comprobarPared(pacman,pacman.getPos().getX(),pacman. getPos().getY()+pacman.getVelocidad()) ) {
                    getPacman().mover();  
                    
                }
                break;
        }
        
        // se actualiza el estado de la posicion de pacman
        
    }
    /**
     * acciones que realiza el controlador por cada iteracion del bucle del juego para actualizar el estado de pacman
     */
    public void cicloPacman(){
        movimiento();
        comer();
        pacmanGana();
    
    }
    /**
     * vuelve a inicializar los valores de pacman al estado inicial
     * @param laberinto
     */
    public void reinicarPacman(Mapa laberinto){
        pacman.getPuntos().clear();
        pacman.getFantasmasComidos().clear();
        pacman.setPuntuacion(0);
        pacman.posicionInicial();
        pacman.setVelocidad(1);
        pacman.setVidas(1);
        pacman.getTeclado().setMove(0);
        pacman.setDireccion(0);
        pacman.setMapa(laberinto);
       
    }
    
    public void Dibujar(Graphics g,Pantalla p){
        this.getPacman().Dibujar(g, p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // se ejecuta cuando termine el tiempo del modo azul de los fantasmas
        // devolviendo a los fantasmas a su modo normal y estableciendo la velocidad de movimiento
        // de pacman a 1
        for(Fantasma f:fantasmas){
            
            if(f.isAzul()){
                f.setAzul(false);
            }
            timerAzul.stop();
            getPacman().setVelocidad(1);
        }
    }

    /**
     * @return the pacman
     */
    public Heroe getPacman() {
        return pacman;
    }

    /**
     * @return the gana
     */
    public boolean isGana() {
        return gana;
    }

    /**
     * @param gana the gana to set
     */
    public void setGana(boolean gana) {
        this.gana = gana;
    }

    /**
     * @return the fin
     */
    public boolean isFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(boolean fin) {
        this.fin = fin;
    }

}
