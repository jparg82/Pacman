/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase que implementa los controles del teclado y da funcionalidad a las teclas del juego ;
 * @author weejdu01
 */
public class Teclado implements KeyListener{
    
    private int move; // proximo movimiento en funcion de la tecla pulsada, 1=izq , 2=dcha , 3=arriba, 4=abajo
    private boolean pausa; // para saber si se pulso la tecla de pausa
    private boolean empezar; // para saber si se pulso la tecla de empezar el juego

    public Teclado() {
        
        pausa=false;
        empezar=false;
        move =2;
    }

    /**
     * @return the move
     */
    public int getMove() {
        return move;
    }

    /**
     * @param move the move to set
     */
    public void setMove(int move) {
        this.move = move;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
            // si la tecla pulsada es hacia arriba se guarda el proximo movimiento como arriba
            if(key == KeyEvent.VK_UP  ){
               
                setMove(3);
                     
            }
            
            // si la tecla pulsada es hacia abajo se guarda el proximo movimiento como abajo
            if(key == KeyEvent.VK_DOWN){
                
                setMove(4);

            }
            // si la tecla pulsada es hacia la izquierda se guarda el proximo movimiento como izquierda
            if(key == KeyEvent.VK_LEFT){
              
                setMove(1);
            }
            // si la tecla pulsada es hacia la derecha se guarda el proximo movimiento como derecha
            if(key == KeyEvent.VK_RIGHT){
                
                setMove(2);

            }
            // si la tecla pulsada es p se modifica el estado de pausa
            if(key==KeyEvent.VK_P){
                setPausa(!isPausa());
            }
            
            // si la tecla pulsada es enter , se pone a true la variable empezar
            if(key==KeyEvent.VK_ENTER){
                setEmpezar(true);
            }
            
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    /**
     * @return the pausa
     */
    public boolean isPausa() {
        return pausa;
    }

    /**
     * @param pausa the pausa to set
     */
    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    /**
     * @return the empezar
     */
    public boolean isEmpezar() {
        return empezar;
    }

    /**
     * @param empezar the empezar to set
     */
    public void setEmpezar(boolean empezar) {
        this.empezar = empezar;
    }
}
