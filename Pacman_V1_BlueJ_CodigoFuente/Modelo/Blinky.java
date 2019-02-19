/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import javax.swing.ImageIcon;

/**
 * Clase del fantasma Blinky, este fantasma sigue a pacman por el laberinto intentando
 * colisionar con el primero verticalmente y luego horizontalmente ,extiende a la clase Fantasma
 * @author Juan Paulo
 */
public class Blinky extends Fantasma{

    /**
    * Constructor de la clase Blinky
    * @param pos posicion inicial
    * @param ancho ancho del personaje
    * @param alto alto del personaje
    * @param velocidad velocidad de desplazamiento del personaje
    * @param mapa mapa en el que se desplaza el personaje
    * @param imagenf imagen del personaje
    */
    public Blinky(Posicion pos, int ancho, int alto, int velocidad, Mapa mapa,String imagenf){
        super(pos, ancho, alto, velocidad, mapa);
        
        f = imagenf;
        fa = "Imagenes/fantasmaAzul.png";
        
        ii_f = new ImageIcon(this.getClass().getResource(f));
        this.imagen= ii_f.getImage();
        
        ii_fa = new ImageIcon(this.getClass().getResource(fa));
    }  
    /**
     * metodo con el movimiento especifico de blinky
     * persigeu a pacman primero verticalmente y luego horizontalmente
     * @param pacman 
     */
    public void movimiento(Heroe pacman){
        
        // si la coordenada y del fantasma es menor a la de pacman y no choca contra la pared
        // el fantasma se movera hacia arriba
        if(this.getPos().getY()<pacman.getPos().getY()){
            if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()+velocidad)){

                setDireccion(4);
            }
        }else{
            // si la coordenada y del fantasma es mayor a la de pacman y no choca contra la pared
            // el fantasma se movera a hacia abajo
            if(this.getPos().getY()>pacman.getPos().getY()){
                if(!mapa.comprobarPared(this,pos.getX(), this.pos.getY()-velocidad)){

                    setDireccion(3);
                }
            }
        }     
        // si la coordenada x del fantasma es menor a la de pacman y no choca contra la pared
        // el fantasma se movera a la derecha
        if(this.getPos().getX()<pacman.getPos().getX()){
            if(!mapa.comprobarPared(this,pos.getX()+velocidad, this.pos.getY())){
                setDireccion(2);
            }
        }else{
            // si la coordenada x del fantasma es mayor a la de pacman y no choca contra la pared
            // el fantasma se movera a la izquierda
            if(this.getPos().getX()>pacman.getPos().getX()){
                if(!mapa.comprobarPared(this,pos.getX()-velocidad, this.pos.getY())){

                    setDireccion(1);
                }
            }
        }   
    }  
   
}
