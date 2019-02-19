/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * Clase del fantasma Clyde, este fantasma no sigue sigue a pacman por el laberinto si no que deambula
 * por el sin rumbo, cambia su direccion aleatoreamente cada cierto tiempo
 * extiende a la clase Fantasma 
 * @author Juan Paulo
 */
public class Clyde extends Fantasma implements ActionListener  {
    
    private Random genererDirecion; // generador de direccion aleatorea
    private Timer cambiarDireccion; // timer que establece cada cuanto tiempo cambia de direccion
    private int direccionAleatoria; // variable que contiene la direccion aleatorea generada

   /**

    * Constructor de la clase Clyde
    * @param pos posicion inicial
    * @param ancho ancho del personaje
    * @param alto alto del personaje
    * @param velocidad velocidad de desplazamiento del personaje
    * @param mapa mapa en el que se desplaza el personaje 
    * @param imagen imagen del personaje
    */
    public Clyde(Posicion pos, int ancho, int alto, int velocidad, Mapa mapa,String imagen){
        super(pos, ancho, alto, velocidad, mapa);
        f = imagen;
        fa = "Imagenes/fantasmaAzul.png";
        
        ii_f = new ImageIcon(this.getClass().getResource(f));
        this.imagen= ii_f.getImage();
        
        ii_fa = new ImageIcon(this.getClass().getResource(fa));

        genererDirecion = new Random();
        cambiarDireccion = new Timer(300,this);
        cambiarDireccion.start();
        direccionAleatoria=0;
        
    }
    /**
     * moviemiento especifico de clyde
     * Asigna una direccion aleatoria a la direccion de moviento del fantasma
     * @param pacman 
     */
    public void movimiento(Heroe pacman){
        this.setDireccion(direccionAleatoria);
    }
    
    /**
     * Metodo que genera una direccion aleatorea
     */
    private void obtenerDireccion(){
        // se obtiene una direccion aleatorea entre 1 y 4
        int dir = genererDirecion.nextInt(5);
        
        if(dir>0 || dir<5){
            // se modifica la direccion del fantasma
            //this.setDireccion(dir);
            direccionAleatoria=dir;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        obtenerDireccion();
    }

}
