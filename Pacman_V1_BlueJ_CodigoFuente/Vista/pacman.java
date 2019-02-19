


package Vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Clase que crea un JFrame que contiene el tablero del juego
 * Es tambien la clase principal del juego
 * @author Juan Paulo
 */
public class pacman extends JFrame{
    
    Pantalla pantalla;
    /**
     * Constructor
     */
    public pacman() {
        crearJFrame(); 
    }
    /**
     * Crea el JFrame
     */
    private void crearJFrame() {
        pantalla = new Pantalla();
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(pantalla);
        pantalla.setBounds(10, 10, 800, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 720);
        setLocationRelativeTo(null);
        setTitle("R - Type ");
        setResizable(false);
        setVisible(true);
    }
    /**
     * Metodo main
     * @param args 
     */
     public static void main (String [] args){
       SwingUtilities.invokeLater(new Runnable(){  
            public void run(){  
                new pacman();
            }  
        }); 
   }
    
}
