/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.controladorJuego;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * clase que contiene el panel donde se dibujara los componentes del juego
 * @author Juan Paulo
 */
public class Pantalla extends JPanel implements ActionListener  {
    
    private controladorJuego controlador;// controlador del juego
    private int ancho; // ancho de la pantalla
    private int alto;// alto de la pantalla
    private Timer timer; // timer que se llamara para ejecutar el bucle del juego y actualizar la parte de la vista
    
    /**
     * Constructor
     */
    public Pantalla() {
        
        controlador = new controladorJuego();
        this.addKeyListener(controlador.getControlPacman().getPacman().getTeclado());
        timer = new Timer(5,this);
        timer.start();
        configurarPantalla();
   
    }
    /**
     * se configura la pantalla
     */
    private void configurarPantalla() {
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        alto=660;
        ancho=630;
        this.setSize(ancho, alto);
        this.setVisible(true);
    }
    /**
     * Metodo paint redefinido para dibujar los distintos componentes del juego
     * @param g 
     */
    public void paint(Graphics g ) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d =(Graphics2D) g;
        RenderingHints rh= new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g2d.setRenderingHints(rh);
        //controlador.Dibujar(g2d, this);
        controlador.Dibujar(g2d, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
        g.dispose();
        
        
    }

    /**
     * @return the controlador
     */
    public controladorJuego getControlador() {
        return controlador;
    }


    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controlador.bucleJuego();
        //this.repaint();
        updateUI();
    }
 
}
