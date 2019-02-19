/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Mapa;
import Modelo.Pared;
import Modelo.Objetos;
import Vista.Pantalla;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Clase que controla el bucle del juego , y mantiene actualizada la parte del modelo a traves de los controladores de pacman y los fantasmas
 * y la vista mediante el metodo dibujar
 * @author Juan Paulo
 */
public class controladorJuego {

    private controladorPacman controlPacman;//controlador pacman
    private controladorFantasmas controlFantasmas; // controlador fantasmas

    private boolean empezar; // indica si empieza el juego
    private boolean pausa; // indica si el juego esta en pausa
    
    private String texto; // texto que se muestra en pantalla tanto al iniciar la partida como cuando termina
    
    /**
     * Constructor
     */
    public controladorJuego() {            
        iniciarJuego();
    }
    
    /**
     * inicializa los controladores del juego
     */
    private void iniciarJuego(){
        
        Mapa laberinto = new Mapa();
        controlFantasmas = new controladorFantasmas(laberinto);
        
        //controlFantasmas.addFantasma(new fatasmaErrante(1,laberinto,new Posicion(390,360)));
        controlPacman = new controladorPacman(laberinto,getControlFantasmas().getFantasmas());
        empezar=false;
        pausa=false;
        texto="pulse enter para empezar";
    }
    /**
     * reinicia los controladores del juego
     */
    private void reiniciarJuego(){
        empezar=false;
        pausa=false;
        Mapa laberinto = new Mapa();
        this.controlFantasmas.reiniciarFantasmas(laberinto);
        this.controlPacman.reinicarPacman(laberinto);
    }
    /**
     * Dibuja los componentes del juego
     * @param g objeto Graphics
     * @param p pantalla donde se dibujan los objetos
     */
    public void Dibujar(Graphics g,Pantalla p){
        dibujarLaberinto(g,p);
        dibujarTexto(g,p);
        controlPacman.Dibujar(g, p);
        controlFantasmas.Dibujar(g, p);
    }
    /**
     * Dibuja el laberinto
     * @param g objeto Graphics
     * @param p pantalla donde se dibujan los objetos 
     */
    private void dibujarLaberinto(Graphics g,Pantalla pa){
        for(int i=0;i<controlPacman.getPacman().getMapa().getParedes().size();i++){
               Pared p= controlPacman.getPacman().getMapa().getParedes().get(i);
               p.Dibujar(g, pa);
               //System.out.println("Pared : " +p.getPos().getX()/30+ "," +p.getPos().getY()/30+ ","+ +p.getTipo() );
               //Toolkit.getDefaultToolkit().sync();
           }
           
           for(int i=0;i<controlPacman.getPacman().getMapa().getPuntos().size();i++){
               Objetos p = controlPacman.getPacman().getMapa().getPuntos().get(i);
               p.Dibujar(g, pa);
               //System.out.println("Pared : " +p.getPos().getX()/30+ "," +p.getPos().getY()/30+ ","+ +p.getTipo() );
               //Toolkit.getDefaultToolkit().sync();
           }
          
    }
    /**
     * Dibuja la parte de texto del juego
     * @param g objeto Graphics
     * @param p pantalla donde se dibujan los objetos 
     */
    private void dibujarTexto(Graphics g,Pantalla p){
        Font boldFont = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics fm=g.getFontMetrics();
        int ancho=fm.stringWidth(texto);
        g.setFont(boldFont);
        g.setColor(Color.WHITE);
        g.drawString("Puntos", 660, 30);
        g.drawString(""+getControlPacman().getPacman().getPuntuacion(), 660, 60);
        g.drawString("Vidas : "+getControlPacman().getPacman().getVidas(), 660, 90);
        //System.out.println("Empezar : " +empezar);
        //System.out.println("Fin : " +this.controlPacman.isFin());
        g.drawString(texto, p.getAncho()/2-ancho, p.getAlto()/2);

    }

 

    /**
     * @return the controlPacman
     */
    public controladorPacman getControlPacman() {
        return controlPacman;
    }

    /**
     * @return the controlFantasmas
     */
    public controladorFantasmas getControlFantasmas() {
        return controlFantasmas;
    }
    /**
     * Metodo que contiene las acciones que debe realizar el juego en cada iteracion.
     */
    public void bucleJuego() {
        // bucle del juejo
       
        pausa=controlPacman.getPacman().getTeclado().isPausa();
        empezar=controlPacman.getPacman().getTeclado().isEmpezar();
        
        // si el juego esta terminado y se empieza el juego pulsando enter
        // se inicia el juego
        if(empezar && controlPacman.isFin()){
            // se modifica la variable de la clase Teclado empezar a false
            controlPacman.getPacman().getTeclado().setEmpezar(false);
            //this.iniciarJuego();
            reiniciarJuego();
            controlPacman.setFin(false);
            texto="";
        }

        // si el juego no termino
        if(!controlPacman.isFin()){
            // solo se movera pacman y se repintara la imagen si el juego no esta en pausa
            if(!pausa){
                texto="";
                // se ejecuta un ciclo de pacman
                getControlPacman().cicloPacman();
                // se ejecuta un cilo de fantasma
                getControlFantasmas().cicloFantasma(getControlPacman().getPacman());
               
                // si el juego termina se ve si es porque el jugador ha ganado
                // o porque pacman colisiono con algun fantasma y se modifica la variable texto
                // que muestra el mensaje en pantalla adecuado
                if(controlPacman.isFin() ){
                    // se modifica la variable de la clase Teclado empezar a false
                    controlPacman.getPacman().getTeclado().setEmpezar(false);
                    if(controlPacman.isGana()){
                        texto="Ha ganado, pulse enter para empezar de nuevo";
                    }else{
                        texto="juego terminado, pulse enter para empezar de nuevo";
                    }

                }      
            }else{
                texto="Juego en pausa";
            }
            
        }
    }
    
}
