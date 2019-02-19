/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Blinky;
import Modelo.Clyde;
import Modelo.Fantasma;
import Modelo.Heroe;
import Modelo.Mapa;
import Modelo.Personaje;
import Modelo.Pinky;
import Modelo.Posicion;
import Vista.Pantalla;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Clase que control las acciones y acutaliza el estado de los fantasmas durante el juego 
 * La colision entre fantasmas realiza un cambio de direccion entre fantasmas durante un periodo un tiempo determinado
 * 
 * @author Juan Paulo
 */
public class controladorFantasmas implements ActionListener{
    
    private ArrayList<Fantasma> fantasmas;// lista de fantasmas
    private Timer noSeguirPacman; // timer que indica el tiempo de duracion de un cambio de direccion cuando colisionan 2 fantasmas
    
    /**
     * Constructor
     * @param laberinto mapa del juego
     */
    public controladorFantasmas(Mapa laberinto) {
        
        fantasmas= new ArrayList<Fantasma>();
        
        String pinky="/Modelo/Imagenes/pinky.png";
        String blinky="/Modelo/Imagenes/blinky.png";
        String clyde="/Modelo/Imagenes/clyde.png";
        addFantasma(new Blinky(new Posicion(210,240),30,30,1,laberinto,blinky));
        addFantasma(new Pinky(new Posicion(390,240),30,30,1,laberinto,pinky));
        addFantasma(new Clyde(new Posicion(210,360),30,30,1,laberinto,clyde));
    }
    /**
     * metodo que comprueba si colisionan los fantasmas y establece el comportamiento sgun colisionen o no
     */
    private void colisionFantasmas(){
        
        for(Fantasma f : fantasmas ){
            
            for(Fantasma faux : fantasmas ){
                // se comprueba que no sea el mismo fantasma
                if(!f.equals(faux)){
                    Rectangle fr;
                    Rectangle fraux;
                    fr = f.getBounds(f.getPos().getX(), f.getPos().getY(), f.getAncho(), f.getAlto());
                    fraux = faux.getBounds(faux.getPos().getX(), faux.getPos().getY(), faux.getAncho(), faux.getAlto());
                    // si los fantasmas colisionan
                    if(fr.intersects(fraux)){
                        // se incializa el timer con el tiempo durante el que cambian de direccion
                        // se modifica el estado de colision de los fantasmas a true
                        f.setColision(true);
                        noSeguirPacman=new Timer(1000,this);
                        noSeguirPacman.setRepeats(false);
                        noSeguirPacman.start();
                        // cambio de direccion del fantasm
                        f.cambioDeDireccion(faux);
                        
                    }
                    
                }
            }
        }
    }
    /**
     * Actualiza la posicion del personaje pasado como parametro si no choca con alguna pared
     * @param p personaje
     */
    private void movimiento(Personaje p){
        Mapa mapa= p.getMapa();
            
            switch (p.getDireccion()){
            // se actualiza el estado de la posicion de pacman en funcion del codigo de direccion
            case 1:
                if(!mapa.comprobarPared(p,p.getPos().getX()-p.getVelocidad(),p. getPos().getY()) ) {
                    p.mover();  
                    
                }
                break;
            case 2:
                if(!mapa.comprobarPared(p,p.getPos().getX()+p.getVelocidad(),p. getPos().getY()) ) {
                    p.mover();  
                   
                }
                break;
            case 3:
                if(!mapa.comprobarPared(p,p.getPos().getX(),p. getPos().getY()-p.getVelocidad()) ) {
                    p.mover();  
               
                }
                break;
            case 4:
                if(!mapa.comprobarPared(p,p.getPos().getX(),p. getPos().getY()+p.getVelocidad()) ) {
                    p.mover();  
                    
                }
                break;
        
        }
        
    }
    /**
     * Metodo que mueve a los fantasmas
     * @param pacman 
     */
    private void moverFantasmas(Heroe pacman){
        // se recorre la lista de fantasmas
        for(Fantasma f:getFantasmas()){
            // si el fantasma es azul y no colisiono con otro fantasma entonces huira de pacmam
            if(f.isAzul()){    
                if(!f.isColision()){
                    f.huirPacman(pacman);
                }   
            }else{
                // si no colisiona con otro fantasma y no es azul entonces el fantasma se mueve en funcion de su movimiento caracteristico
                if(!f.isColision()){
                    f.movimiento(pacman);
                }
            }
            System.out.println("Direccion fantasma : "+ f.getDireccion());
            // se actualiza el estado de la posicion del fantasma
            movimiento(f);
                
        }
    }
    /**
     * acciones que realiza el controlador por cada iteracion del bucle del juego para actualizar el estado de los fantasmas
     * @param pacman 
     */
    public void cicloFantasma(Heroe pacman){
        colisionFantasmas();
        moverFantasmas(pacman);      
    }
    /**
     * vuelve a inicializar los valores de los fantasmas al estado inicial
     * @param laberinto
     */
    public void reiniciarFantasmas(Mapa laberinto){
        
        for(Fantasma f: fantasmas){       
            f.setAzul(false);
            f.posicionInicial(); 
            f.setColision(false); 
            f.setMapa(laberinto);
        }
    }
    
    public void Dibujar(Graphics g,Pantalla p){
        for(Fantasma f :getFantasmas()){
                f.Dibujar(g, p);
           }
    }

    /**
     * @return the fantasmas
     */
    public ArrayList<Fantasma> getFantasmas() {
        return fantasmas;
    }

    /**
     * @param fantasmas the fantasmas to set
     */
    public void setFantasmas(ArrayList<Fantasma> fantasmas) {
        this.fantasmas = fantasmas;
    }
    
    public void addFantasma(Fantasma fantasma){
        fantasmas.add(fantasma);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Fantasma f:getFantasmas()){
            // se actualiza el estado de colision entre los fantasmas a false
            if(f.isColision()){
                f.setColision(false);
            }
            noSeguirPacman.stop();  

        }
    }
  
}
